package com.restexamples;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
	private Connection con = null;
	private Statement st = null;
	private ResultSet rs = null;

	public List<User> getAllUsers() {

		List<User> userList = null;
//		try {
//			File file = new File("Users.dat");
//			if (!file.exists()) {
				// create the java statement
				con = ConnectionManager.getConnection();

				try {
					st = con.createStatement();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String query = "SELECT * FROM user";
				// execute the query, and get a java result

				try {
					rs = st.executeQuery(query);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				userList = new ArrayList<User>();
				// iterate through the java result
				try {
					while (rs.next()) {
						int id = rs.getInt("id");
						String name = rs.getString("name");
						String profession = rs.getString("profession");
						System.out.println("id:"+id+"name:"+name+"prof:"+profession);
						User user = new User(id, name, profession);
						userList.add(user);

					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					st.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
//				saveUserList(userList);

//			} else {
//				FileInputStream fis = new FileInputStream(file);
//				ObjectInputStream ois = new ObjectInputStream(fis);
//				userList = (List<User>) ois.readObject();
//				ois.close();
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}
		return userList;
	}

	private void saveUserList(List<User> userList) {
		try {
			File file = new File("Users.dat");
			FileOutputStream fos;
			fos = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(userList);
			oos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
