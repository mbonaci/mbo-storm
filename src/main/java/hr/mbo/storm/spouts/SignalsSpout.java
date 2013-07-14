package hr.mbo.storm.spouts;

import java.util.Map;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichSpout;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;

public class SignalsSpout extends BaseRichSpout{

	private static final long serialVersionUID = 5903206058778937258L;
	
	private SpoutOutputCollector collector;


	public void nextTuple() {
		collector.emit("signals", new Values("refreshCache"));
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {}
	}

	public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
		this.collector = collector;
	}

	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declareStream("signals", new Fields("action"));
	}

}