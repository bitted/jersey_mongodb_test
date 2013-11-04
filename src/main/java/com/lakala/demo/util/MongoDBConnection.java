package com.lakala.demo.util;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.jmkgreen.morphia.AdvancedDatastore;
import com.github.jmkgreen.morphia.Datastore;
import com.github.jmkgreen.morphia.Morphia;
import com.mongodb.DB;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;

/**
 * 类名称：MongoDBConnection
 * 类描述：(Mongodb连接)
 * 创建人：litj
 * 创建时间：2013-7-9 上午09:57:02
 * 修改人：
 * 修改时间：2013-7-9 上午09:57:02
 * 修改备注：
 * 
 * @version 1.0.0
 */
public class MongoDBConnection {
	protected static Logger logger = LoggerFactory.getLogger(MongoDBConnection.class);

	private static Mongo instance = null;

	public static Datastore getDataStore(String dbname) {
		Morphia morphia = new Morphia();
		AdvancedDatastore ads = (AdvancedDatastore) morphia.createDatastore(getMongo(), dbname);
		return ads;
	}

	public static DB getDB(String dbname) {
		return getMongo().getDB(dbname);
	}

	/**
	 * instance
	 * 
	 * @return the instance
	 * @since 1.0.0
	 */

	public static Mongo getInstance() {
		return instance;
	}

	public static synchronized Mongo getMongo() {
		if (instance == null) {

			try {
				List<ServerAddress> saList = new ArrayList<ServerAddress>();
				saList.add(new ServerAddress(LKLConfig.getValue("mongoDBIP"), Integer.parseInt(LKLConfig.getValue("mongoDBPORT"))));
				/**
				 * 如果采用的是Replica Set集群模式或则Sharding模式，使用下面的配置，提供并发量和数据存储安全
				 */
				// saList.add(new ServerAddress(LKLConfig.mongoIp2, Integer.parseInt(LKLConfig.mongoPort2)));
				// saList.add(new ServerAddress(LKLConfig.mongoIp3, Integer.parseInt(LKLConfig.mongoPort3)));

				// List<MongoCredential> credentialsList = new ArrayList<MongoCredential>();
				// MongoCredential credentials = MongoCredential.createMongoCRCredential(LKLConfig.mongoUserName,
				// LKLConfig.mongoName, LKLConfig.mongoPassWord.toCharArray());
				// credentialsList.add(credentials);

				MongoClientOptions.Builder builder = new MongoClientOptions.Builder();
				MongoClientOptions options = builder.build();

				/**
				 * mongo描述
				 */
				builder.description("Lakala Mongo ！");
				/**
				 * 是否连接自动重试
				 */
				builder.autoConnectRetry(true);
				/**
				 * 连接数
				 */
				builder.connectionsPerHost(Integer.valueOf(LKLConfig.getValue("mongo.mongodbConnCount")));
				/**
				 * 连接超时时间
				 */
				builder.connectTimeout(30000);
				/**
				 * 最大等待时间
				 */
				builder.maxWaitTime(120000);
				/**
				 * 保持连接
				 */
				builder.socketKeepAlive(true);
				/**
				 * 0，不限时间
				 */
				builder.socketTimeout(0);
				/**
				 * 最大重试时间，单位秒
				 */
				builder.maxAutoConnectRetryTime(1);

				/**
				 * 这个乘数,乘以这个connectionsPerHost,来设置一个连接的最大连接池等待数量例如,
				 * 如果connectionsPerHost是10和threadsAllowedToBlockForConnectionMultiplier是5,那么多达50个线程可以等待连接。
				 * 默认值是5。
				 */
				builder.threadsAllowedToBlockForConnectionMultiplier(50);

				/**
				 * 设置MongoClient，包括集群地址和数据库连接参数配置
				 */
				instance = new MongoClient(saList);
				// instance = new MongoClient(saList, credentialsList);
				/**
				 * 设置从secondaries集合中读取
				 */
				// instance.setReadPreference(ReadPreference.secondaryPreferred());
			} catch (UnknownHostException e) {
				logger.error("Mongon数据库服务器连接失败！", e);
				return null;
			}

		}
		return instance;
	}

	/**
	 * @param instance
	 *            the instance to set
	 */

	public static void setInstance(Mongo instance) {
		MongoDBConnection.instance = instance;
	}
}
