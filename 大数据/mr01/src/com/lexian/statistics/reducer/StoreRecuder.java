package com.lexian.statistics.reducer;

import java.io.IOException;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import com.lexian.bean.CommodityStatistics;
import com.lexian.bean.StoreStatistics;

public class StoreRecuder extends Reducer<Text, NullWritable, StoreStatistics, NullWritable>{

	@Override
    protected void reduce(Text key, Iterable<NullWritable> values, Context context)
            throws IOException, InterruptedException {
        int count = 0 ;
        for (NullWritable v:values){
        	count ++ ;
        }
        StoreStatistics ss = new StoreStatistics() ;
        
        String[] typeAndNo=key.toString().split("_");
        Integer type=Integer.parseInt(typeAndNo[0]);
        String sno=typeAndNo[1];
        String cno=typeAndNo[2];
        ss.setType(type);
        ss.setCount(count);
        ss.setCommodityNo(cno);
        ss.setStoreNo(sno);
        
        context.write(ss, NullWritable.get());
    }
}
