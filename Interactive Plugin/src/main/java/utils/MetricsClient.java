package utils;

import com.intellij.openapi.application.PermanentInstallationID;
import dialogs.CardLayoutDialog;
import dialogs.DebugNotification;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
//import io.github.cdimascio.dotenv.Dotenv;
import window_factory.BillingToolWindowFactory;

import javax.swing.filechooser.FileSystemView;

public class MetricsClient {
    HttpClient client;
    PermanentInstallationID id;
    String requestString;

    ExecutorService executorService;

    public MetricsClient(){
        client = new HttpClient();
        id = new PermanentInstallationID();
        executorService = Executors.newFixedThreadPool(10);
        requestString = Constants.METRICS_PROD;
    }

    public void registerAction(Actions action) {
        String request = String.format(requestString, id.get(), action);
        PostMethod method = new PostMethod(request);
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    client.executeMethod(method);
                } catch (IOException e) { }
            }
        });
    }
}
