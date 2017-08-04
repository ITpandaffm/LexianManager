package com.lexian.statistics.reducer;

import java.io.IOException;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import com.lexian.bean.CommodityStatistics;

public class CommodityRecuder extends Reducer<Text, NullWritable, CommodityStatistics, NullWritable>{

	@Override
    protected void reduce(Text key, Iterable<NullWritable> values, Context context)
            throws IOException, InterruptedException {
        int count = 0 ;
        for (NullWritable v:values){
        	count ++ ;
        }
        CommodityStatistics cb = new CommodityStatistics() ;
        
        String[] typeAndNo=key.toString().split("_");
        Integer type=Integer.parseInt(typeAndNo[0]);
        String no=typeAndNo[1];
        cb.setType(type);
        cb.setCount(count);
        cb.setCommodityNo(no);
        
        context.write(cb, NullWritable.get());
    }
}
