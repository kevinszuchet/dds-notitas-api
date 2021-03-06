package server;

import spark.Spark;
import spark.debug.DebugScreen;

public class Server {
	public static void main(String[] args) {
		new DatosDePrueba().init();
		Spark.port(9000);
		DebugScreen.enableDebugScreen();
		new Router().configure();
	}

}
