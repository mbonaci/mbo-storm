package hr.mbo.storm.groupings;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import backtype.storm.generated.GlobalStreamId;
import backtype.storm.grouping.CustomStreamGrouping;
import backtype.storm.task.WorkerTopologyContext;

public class ModuleGrouping implements CustomStreamGrouping, Serializable {

	private static final long serialVersionUID = 5972450807554979740L;

	int numTasks = 0;
	
	public void prepare(WorkerTopologyContext context, GlobalStreamId stream, List<Integer> targetTasks) {
		this.numTasks = targetTasks.size();
		for(Integer i : targetTasks)
			System.out.println("targetTask " + i);
	}

	public List<Integer> chooseTasks(int taskId, List<Object> values) {
		List<Integer> boltIds = new ArrayList<Integer>();
		
		if(values.size() > 0){
			String str = values.get(0).toString();
			if(str.length() == 0)
				boltIds.add(0);
			else
				boltIds.add(str.charAt(0) % numTasks);
		}
		return boltIds;
	}

}
