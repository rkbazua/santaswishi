'use strict'

const MongoClient = require('mongodb').MongoClient;

// replace the uri string with your connection string.
const uri = "mongodb+srv://lambdauser:lambdauser@cluster0-xexqw.mongodb.net/admin?retryWrites=true";

exports.connectMongo = (event, context, callback) => {
	const uri = "mongodb+srv://lambdauser:lambdauser@cluster0-xexqw.mongodb.net/admin?retryWrites=true";
	var mongoResult = null;
	MongoClient.connect(uri, function(err, db) {
	   if (err) throw err;
		  var dbo = db.db("petrescue");
		  dbo.collection("pet").find({}).toArray(function(err, result) {
			if (err) throw err;
			console.log(result);
			db.close();
			callback(null, {"statusCode": 200, "body": JSON.stringify(result)});
		  });
	});
};