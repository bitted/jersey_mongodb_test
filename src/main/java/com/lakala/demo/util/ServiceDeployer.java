package com.lakala.demo.util;

import java.io.IOException;
import java.net.URI;

import javax.ws.rs.core.UriBuilder;

import org.glassfish.grizzly.http.server.HttpServer;

import com.sun.jersey.api.container.grizzly2.GrizzlyServerFactory;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;

public class ServiceDeployer {

	private static URI getBaseURI() {
		return UriBuilder.fromUri(LKLConfig.getValue("jerseyUrl")).port(Integer.valueOf(LKLConfig.getValue("jerseyPort")))
				.build();
	}

	public static final URI BASE_URI = getBaseURI();

	protected static HttpServer startServer() throws IOException {
		System.out.println("Starting grizzly...");
		ResourceConfig rc = new PackagesResourceConfig("com.lakala.demo.resource");
		return GrizzlyServerFactory.createHttpServer(BASE_URI, rc);
	}

	public static void main(String[] args) throws IOException {
		/**
		 * 加载Spring的配置文件
		 */
		SpringUtils.start();

		/**
		 * 启动Grizzly web Server
		 */
		HttpServer httpServer = startServer();
		System.out.println(String.format("Jersey app started with WADL available at "
				+ "%sapplication.wadl\nHit enter to stop it...", BASE_URI, BASE_URI));
		System.in.read();
		httpServer.stop();
	}
}