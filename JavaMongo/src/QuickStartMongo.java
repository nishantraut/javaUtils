import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;

/**
 * Sample class demonstrating mongo db-java driver capabilities.
 * 
 * @author Nishant
 *
 */
public class QuickStartMongo {
	public static void main(String[] args) {
		try {

			/**
			 * Connect to mongo db on default host and port we can specify if
			 * you have changed
			 */
			MongoClient mongoClient = new MongoClient("localhost", 27017);

			/**
			 * using mongo client get or create the database named 'company' if
			 * not exists it will create new.
			 */
			DB db = mongoClient.getDB("company");

			/**
			 * then using db handle get or create table/collection named
			 * 'employee' if not exists in connected db it will create new one.
			 */
			DBCollection collection = db.getCollection("employee");

			/**
			 * lets create new document/tuple to insert in given collection/
			 * table.
			 */
			BasicDBObject doc = new BasicDBObject();
			doc.put("eId", 1);
			doc.put("eName", "abc");
			doc.put("eDept", "IT");
			collection.insert(doc);

			/**
			 * lets search the record whose eName is abc using find method
			 */
			BasicDBObject query = new BasicDBObject();
			query.put("eName", "abc");

			DBCursor dbCursor = collection.find(query);

			while (dbCursor.hasNext()) {
				System.out.println(dbCursor.next());
			}

			/**
			 * lets search the document with eName is abc and update eName to
			 * abc-xyz using update method
			 */
			BasicDBObject updateQuery = new BasicDBObject();
			updateQuery.put("eName", "abc");

			BasicDBObject newDocument = new BasicDBObject();
			newDocument.put("eName", "abc-xyz");

			BasicDBObject updatedObj = new BasicDBObject();
			updatedObj.put("$set", newDocument);

			collection.update(updateQuery, updatedObj);

			/**
			 * Search by new name and display the record
			 */
			BasicDBObject searchByNewNameQuery = new BasicDBObject().append(
					"eName", "abc-xyz");

			DBCursor searchDbCursor = collection.find(searchByNewNameQuery);

			while (searchDbCursor.hasNext()) {
				System.out.println(searchDbCursor.next());
			}

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (MongoException e) {
			e.printStackTrace();
		}
	}
}
