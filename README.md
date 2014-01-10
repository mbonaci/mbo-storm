## My exploration of [Twitter Storm](http://storm-project.net)
<small>Wow, three build trackers (just because I can):</small>  
[![Codeship Status](https://www.codeship.io/projects/295eecb0-1e30-0131-5e18-56138275c44e/status)](https://www.codeship.io/projects/8468)
[![Travis-ci Build Status](https://travis-ci.org/mbonaci/mbo-storm.png?branch=master)](https://travis-ci.org/mbonaci/mbo-storm)
[![Drone.io Build Status](https://drone.io/github.com/mbonaci/mbo-storm/status.png)](https://drone.io/github.com/mbonaci/mbo-storm/latest)

### Links
* Storm author [Nathan Marz's github repo](https://github.com/nathanmarz/storm)
* My [tutorial on setting up Storm in Eclipse with maven, git and GitHub](https://github.com/mbonaci/mbo-storm/wiki/Storm-setup-in-Eclipse-with-Maven,-Git-and-GitHub)  
* My [reminder note about integrating a GitHub repo with Travis-CI](https://github.com/mbonaci/mbo-storm/wiki/Integrate-Travis-CI-with-your-GitHub-repo)
* My [completely unnecesarry guide for setting up a GitHub project build on Drone.io](https://github.com/mbonaci/mbo-storm/wiki/Completely-unnecesarry-guide-for-setting-up-a-GitHub-project-build-on-Drone.io)

---  

Storm is distributed and fault-tolerant realtime computation platform that offers stream processing, continuous computation, distributed RPC, and more.  


<p>
Storm is a <a href="http://storm-project.net/about/free-and-open-source.html">free and open source</a> distributed realtime computation system. Storm makes it easy to reliably process unbounded streams of data, doing for realtime processing what Hadoop did for batch processing. Storm is <a href="http://storm-project.net/about/simple-api.html">simple</a>, can be used with <a href="http://storm-project.net/about/multi-language.html">any programming language</a>, and is a lot of fun to use!
</p>

<p>
Storm has many use cases: realtime analytics, online machine learning, continuous computation, distributed RPC, ETL, and more. Storm is fast: a benchmark clocked it at over <strong>a million tuples processed per second per node</strong>. It is <a href="http://storm-project.net/about/scalable.html">scalable</a>, <a href="http://storm-project.net/about/fault-tolerant.html">fault-tolerant</a>, <a href="http://storm-project.net/about/guarantees-data-processing.html">guarantees your data will be processed</a>, and is <a href="http://storm-project.net/about/deployment.html">easy to set up and operate</a>.
</p>
<p style="text-align: center;">
  <img src="http://storm-project.net/images/topology.png" style="height: 220px;">
</p>

<p>
Storm <a href="http://storm-project.net/about/integrates.html">integrates</a> with the queueing and database technologies you already use. A Storm topology consumes streams of data and processes those streams in arbitrarily complex ways, repartitioning the streams between each stage of the computation however needed. Read more in <a href="https://github.com/nathanmarz/storm/wiki/Tutorial">the tutorial</a>.
</p>  


[![Bitdeli Badge](https://d2weczhvl823v0.cloudfront.net/mbonaci/mbo-storm/trend.png)](https://bitdeli.com/free "Bitdeli Badge")

