/*
 * Copyright 2007 - 2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.sf.jailer.database;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.UUID;

import net.sf.jailer.ExecutionContext;
import net.sf.jailer.util.ClasspathUtil;


/**
 * Provides a local database (H2).
 * 
 * @author Ralf Wisser
 */
public class LocalDatabase {
	
	/**
	 * The session for the local database.
	 */
	private final Session session;

	/**
	 * Name of the folder containing the local database.
	 */
	private String databaseFolder;
	
	/**
	 * The execution context.
	 */
	private final ExecutionContext executionContext;
	
	/**
	 * Creates a local database.
	 */
	public LocalDatabase(String driverClassName, String urlPattern, String user, String password, String jarfile, ExecutionContext executionContext) throws ClassNotFoundException, FileNotFoundException, SQLException {
		this.executionContext = executionContext;
		this.databaseFolder = executionContext.newFile(executionContext.getTempFileFolder()) + File.separator + UUID.randomUUID().toString();
		executionContext.newFile(databaseFolder).mkdirs();
		BasicDataSource dataSource = new BasicDataSource(driverClassName, urlPattern.replace("%s", databaseFolder + File.separator + "local"), user, password, ClasspathUtil.toURLArray(jarfile, null));
		session = new Session(dataSource, dataSource.dbms, null, false, true);
	}
	
	/**
	 * Shut local database down. Remove all database files.
	 */
	public void shutDown() throws SQLException {
		session.shutDown();
		File localFolder = executionContext.newFile(databaseFolder);
		File[] listFiles = localFolder.listFiles();
		if (listFiles != null) {
			for (File file: listFiles) {
				file.delete();
			}
		}
		localFolder.delete();
	}

	/**
	 * Gets the {@link Session} for the local database.
	 * 
	 * @return the session for the local database
	 */
	public Session getSession() {
		return session;
	}

}
