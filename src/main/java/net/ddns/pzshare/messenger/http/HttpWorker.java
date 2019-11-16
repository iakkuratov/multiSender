package net.ddns.pzshare.messenger.http;

import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import net.ddns.pzshare.messenger.StartException;
import net.ddns.pzshare.messenger.Worker;
import net.ddns.pzshare.messenger.SendException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("WeakerAccess")
public class HttpWorker extends Worker {
    private static final Logger log = LogManager.getLogger();

    private HttpServer server;

    final HttpConfig config;

    public HttpWorker(HttpConfig config){
        this.config = config;
    }

    private Map<String, String> parseQuery(String query) throws ParseException {
        Map<String, String> result = new HashMap<>();

        for (String param : query.split("&")) {
            String[] kv = param.split("=");

            if (kv.length == 2)
                result.put(kv[0], kv[1]);
            else
                throw new ParseException("Failed to read param: " + param, 0);
        }

        return result;
    }

    public void stop() {
        log.info("Stopping http server");

        server.stop(1);
    }

    @Override
    public void start() throws StartException{
        try {
            server = HttpServer.create();
            server.bind(config.getSocket(), 0);

            server.createContext(config.getListenPath(), (http) -> {
                String query = http.getRequestURI().getQuery();

                log.debug("Get request: " + query);

                int resultCode = 200;

                String response = "";

                try {
                    Map<String, String> params = parseQuery(query);

                    String receiverId = params.get("chatId");

                    String text = params.get("text");

                    if (receiverId == null | text == null)
                        throw new NullPointerException("chatId or text argument is empty or both");

                    toSubscribers(receiverId, text);

                } catch (ParseException | NullPointerException | NumberFormatException ex) {
                    resultCode = 400;

                    response = ex.getMessage();

                    log.info("Failed to parse parameters: " + ex.getMessage());
                }

                log.info("Processed request: " + http.getRequestURI()
                        + " with result: " + resultCode
                        + " and response: " + response
                );

                http.sendResponseHeaders(resultCode, response.length());

                OutputStream os = http.getResponseBody();

                os.write(response.getBytes());

                os.close();
            });

            log.info("Starting new http server on port: " + config.getSocket().getPort());

            server.start();
        }catch (IOException ex){
            throw new StartException("Failed to start httpServer " + ex.getMessage());
        }
    }

    @Override
    public void send(String userId, String msg) throws SendException {
        /* NO-OP **/
        throw new SendException("Unsupported sender got send request with text: " + msg);
    }
}