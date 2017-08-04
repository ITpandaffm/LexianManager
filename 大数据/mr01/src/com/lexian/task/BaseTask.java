package com.lexian.task;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.lib.db.DBConfiguration;
import org.apache.hadoop.mapred.lib.db.DBInputFormat;
import org.apache.hadoop.mapred.lib.db.DBOutputFormat;
import org.apache.hadoop.mapreduce.Job;

import com.lexian.utils.DBUtils;

public class BaseTask implements Runnable{
	
	protected String outputTable;
	protected Class inputClass;
	protected Class outputClass;
	protected String querySql;
	protected String countSql;
	
	protected Class mapperClass;
	protected Class recuderClass;
	
	protected String[] outputFields;
	
	private static final String DB="jdbc:mysql://192.168.35.3:3306/lexian";
	private static final String DB_DRIVER="com.mysql.jdbc.Driver";
	private static final String USER="root";
	private static final String PASSWORD="root";
	
	public Date getPreYear(){
		Calendar calendar = Calendar.getInstance();
        Date date = new Date(System.currentTimeMillis());
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, -1);
        date = calendar.getTime();
        return date;
	}
	
	@Override
	public void run() {
		
		
		clearTableRecord();
		
		Configuration conf = new Configuration() ;
        DBConfiguration.configureDB(
                conf, DB_DRIVER,
                DB,USER,PASSWORD);
        
        Job job=prepareJob(conf);
        
		prepareDBInputFormat(job);
		
		prepareDBOutputFormat(job);
		try {
			job.waitForCompletion(true) ;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	private void prepareDBOutputFormat(Job job) {
		try {
			
			DBOutputFormat.setOutput(
			        job,                // job
			        outputTable,       // output table name
			        outputFields     // fields
			);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

	private void prepareDBInputFormat(Job job) {
		DBInputFormat.setInput(
                job,                // job
                inputClass,  // input class
                querySql,
                countSql
                ); 
	}

	private Job prepareJob(Configuration conf) {
		Job job=null;
		try {
			job = Job.getInstance(conf,"lexian_statistics");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        job.setJarByClass(BaseTask.class);
        job.setMapperClass(mapperClass);
        job.setReducerClass(recuderClass);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(NullWritable.class);
        job.setOutputKeyClass(outputClass);
        job.setOutputValueClass(NullWritable.class);
        job.setInputFormatClass(DBInputFormat.class);
        job.setOutputFormatClass(DBOutputFormat.class);
		return job;
	}

	private void clearTableRecord() {
		DBUtils.clearTableRecord(outputTable);
	}
	
}
