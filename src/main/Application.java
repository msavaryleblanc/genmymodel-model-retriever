package main;

import main.java.data.RemoteModelDataSource;

public class Application {

    public static void main(String[] args) {
        RemoteModelDataSource remoteModelDataSource = new RemoteModelDataSource();
        remoteModelDataSource.getAllModelsUntilPage(0, 1);
    }
}
