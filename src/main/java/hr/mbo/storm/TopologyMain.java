package hr.mbo.storm;

import hr.mbo.storm.bolts.WordCounter;
import hr.mbo.storm.bolts.WordNormalizer;
//import hr.mbo.storm.groupings.ModuleGrouping;
import hr.mbo.storm.spouts.SignalsSpout;
import hr.mbo.storm.spouts.WordReader;
import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.topology.TopologyBuilder;
//import backtype.storm.tuple.Fields;

public class TopologyMain {

	public static void main(String[] args) throws InterruptedException {
		try {
			TopologyBuilder builder = new TopologyBuilder();
			builder.setSpout("word-reader", new WordReader());
			builder.setSpout("signals-spout", new SignalsSpout());
			
			builder.setBolt("word-normalizer", new WordNormalizer())
					.shuffleGrouping("word-reader");
			
			builder.setBolt("word-counter", new WordCounter(), 2)
					.shuffleGrouping("word-normalizer")
					.allGrouping("signals-spout", "signals");

			Config conf = new Config();
			conf.put("wordsFile", args[0]);
			conf.setDebug(true);
			conf.put(Config.TOPOLOGY_MAX_SPOUT_PENDING, 1);

			LocalCluster cluster = new LocalCluster();
			cluster.submitTopology("Getting-Started-Toplogie", conf, builder.createTopology());
			
			Thread.sleep(4000);
			
			cluster.shutdown();
			
		} catch(Exception ioe) {
			System.out.println("################ Exception thrown ################");
			ioe.printStackTrace();
		}
	}

}
