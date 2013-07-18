# Setting up a new [Storm](http://storm-project.net/)-based project
This short (ok, relatively short) tutorial will guide you through the process of setting up fresh Twitter Storm project in your Eclipse IDE. You'll be using Maven to automate dependencies, Git to manage versions and GitHub to publish your source. Although Storm is written primarily in Clojure we'll be using Java API. This should be applicable to your OS, whether you're on Linux, Mac or Windows.

### A note about maven
I was really never much of a maven worshiper, if I may say so. I use it because it's so widespread and it does its job (eventually) so that I simply could not afford to ignore it. But there are better tools out there. E.g. a great ruby tool for Java development automation called [Apache Buildr](http://buildr.apache.org/) that's much simpler and easier to use. Unfortunately, we'll have to postpone covering it until it gains more traction in the community. If you want to indulge yourself in more maven's *suckassness*, take a look at [the spymemcached author's great blog post](http://dustin.github.io/2010/04/01/why-not-maven.html). Or [grails author's post](http://graemerocher.blogspot.com/2008/01/why-grails-doesnt-use-maven.html). If not, move on, your loss.

## Relatively quick start
Follow the steps bellow to start this bitch from the ground up:

### Prereqs
I won't waste your time describing how to set up the basics. I'll assume you either already have these or that you'll be able to manage the basic setup yourself.
* [Install latest Java (JRE or JDK)](http://www.oracle.com/technetwork/java/javase/downloads/index.html).
* [Install latest Git](http://git-scm.com/downloads).
* [Install latest Maven](http://maven.apache.org/download.html).
* [Install latest Eclipse](http://www.eclipse.org/downloads).
* [Install latest Eclipse EGit plugin](http://marketplace.eclipse.org/content/egit-git-team-provider).
* [Install latest Eclipse "Maven Integration For Eclipse" plugin](http://marketplace.eclipse.org/content/maven-integration-eclipse).
* [Create <del>latest</del> GitHub account](http://github.com).

[Here](http://rogerdudler.github.io/git-guide/) is a nice, simple and good looking git guide, I quote: "no deep shit!".  
Instead of using GitHub, you can create your own local git "remote" repository (sometimes referred to as "bare repo") like [this](http://treeleaf.be/blog/2011/03/creating-a-new-git-repository-on-a-local-file-system/).  

To check whether maven and git are correctly set up, open terminal (`cmd` on win) and type `mvn -version` and hit enter. Then try `git --version`. Errors? Tough luck. Could be million different things. Use [StackOverflow](http://stackoverflow.com/).


### OK, let's kick some maven ass
Now we're ready to go: 
* If you're already using git, you can step over this step. Otherwise, create a fresh folder that'll end up being the parent folder of our project's root folder. I'll call mine `repos`.
* In terminal, `cd` to your new `repos` or *whatever-you-called-it* dir and type `mvn archetype:generate`. We use this command to tell maven to go and kick off our new project (create the weird pom.xml file that's known to sometimes smell like tomatoes).
* You'll be bombarded with the huge list of maven archetypes. Those are project templates that are used to automatically scaffold your new project.  

<small>Feel free to poke around the list by typing a couple of interesting words and hitting enter between them, like Wicket, Lucene, Solr,...  
Please stop fooling around, quickly, without entering any text hit enter and you'll get the initial listing (yes, the huge one).</small>  
To choose default archetype, `maven-archetype-quickstart`, just hit enter or type 292 (currently 292, but if you've got a different number use that one).
* Next, you're asked to choose the version of `maven-archetype-quickstart` you want to use. Hit Enter to select the default, latest stable version (currently option 6, v1.1).
* Now maven wants to know `groupId`. What a hell is groupId, you ask? That's like a package directory. Like `org.apache` in `org.apache.maven` or `com.your-company` or `countryLocale.yourNickname`, whatever. But be prepared that your project's directory structure will be based on this input. E.g. if you type `my.friend.is.a.seal` you'll end up with a dir `repos/project-name/src/main/java/my/friend/is/a/seal`, so be careful.
* `artifactId`? That's the name of your project. Of course they couldn't name it "projectId", what's wrong with you? In fact, artifact is like a maven general object (e.g. project, module, dependency, plugin). I'll use `my-own-storm`.
* Next, maven wants to know your project's version. Type `0.01.461`. I'm kidding, just hit enter (although that would be a better guess than `1.0-SNAPSHOT`). BTW, if a version contains keyword “-SNAPSHOT”, then maven replaces it with a UTF datetime value at the time of release. That's used to declare to the world that your project is currently under development.
* Next, `package`. We wont go into that. Hit enter.
* Maven now presents you with the choices you made and asks your confirmation. Type `Y` and hit enter.  
`BUILD SUCCESSFUL` no way, you did it! If you examine your newly created project folder, you'll see all the dirs and files that `maven-archetype-quickstart` created for us. That's the standard maven project folder structure. Not much, I know, but we don't need fancy stuff, quickstart'll work just fine, one main class and one test class. 
* To check if you can successfuly build your new, still rather empty project, `cd` to `my-own-storm` and execute `mvn install`. You should see `BUILD SUCCESSFUL` somewhere in your terminal. If you now take a look at project's root, you'll see that this step created `target` folder. That's your project's output folder with compiled java files and some other irrelevant stuff.  

If you encounter some weird error messages, like `use -source 5 or higher to enable static import declarations`, add this to your pom.xml located at project's root (```<build>``` tag should go at the bottom, just above, ```</project>``` tag, like in the next snippet):

```xml
        # ...
        <build>
            <plugins>
                <plugin>
                    <groupId>org.apache.maven.plugins</groupId>
                    <artifactId>maven-compiler-plugin</artifactId>
                    <version>2.3.2</version>
                    <configuration>
                        <source>1.7</source>
                        <target>1.7</target>
                    </configuration>
                </plugin>
            </plugins>
        </build>
    </project>
```

So far, we have created our new `my-own-storm` project, with Storm nowhere to be seen. Next, we're gonna address that by adding Storm dependencies to our `pom.xml`. Dependencies are used by maven to download additional libraries. When you get used to maven, I promise you, you'll never look back (which doesn't mean you wont look ahead). 
* Open `pom.xml` from your project's root in your favorite text editor (not yet Eclipse) and add this repository and dependency definitions:

```xml
    <repositories> 
      <repository> 
        <id>clojars.org</id> 
        <url>http://clojars.org/repo</url> 
      </repository> 
    </repositories> 

    <dependency>
      <groupId>storm</groupId>
      <artifactId>storm</artifactId>
      <version>0.8.2</version>
      <scope>compile</scope>
    </dependency>
```
[Here is the gist of the complete pom.xml](https://gist.github.com/mbonaci/5996278) so you can see where exactly those parts go. I suggest you use only the snippets we mentioned so far. Don't go crazy copying the whole thing, let me ease you into this stuff gently.   
[And here is the actual version of maven setup](https://github.com/nathanmarz/storm/wiki/Maven), maintained by Storm's father, Nathan Marz.  
* Now, after we added Storm to our maven configuration, `cd` to your project's root and execute `mvn clean install`. I unnecessarily added `clean` just to spice things up. It deletes the `target` dir so maven can do fresh install. `BUILD SUCCESSFUL` again? You're a regular genius!

### Now the fun stuff, Git me up Scotty
This is what the fans around the world have been waiting for. Five rounds for the undisputed... Ignore me, please.  
Git is the undisputed scm champion, let's sort out that part of our setup.
* Log in to your github account and create new `my-own-storm` repository. We'll add `README.md` later.  

You can use `https` to communicate with GitHub, but I suggest you [set up SSH](https://help.github.com/articles/generating-ssh-keys) in your local git installation and add it to your GitHub profile.  

* In your terminal, `cd` to `my-own-storm` dir and execute the following set of commands:

```shell
    mvn clean
    git init
    git add --all
    git commit -m "Initial commit"
    # for SSH:
    git remote add origin git@github.com:your_git_username/my-own-storm.git
    # for HTTPS:
    git remote add origin https://github.com/your_git_username/my-own-storm.git
    git push origin master
```

Now, if you open your new GitHub repository, you should see your files neatly stashed there. Good stuff!

### Linking maven to GitHub repo
You can either continue to use git from the command line (in which case you can skip to [Eclipse section](#eclipse)) or you can use maven to talk to your github repository. To do the latter:
* Add these snippets into `pom.xml` (again, [check out](https://gist.github.com/mbonaci/5996278) where to put them if you need):

```xml
    <scm>
      <connection>scm:git:git@github.com:your_github_username/my-own-storm.git</connection>
      <url>scm:git:git@github.com:your_github_username/my-own-storm.git</url>
      <developerConnection>scm:git:git@github.com:your_github_username/my-own-storm.git</developerConnection>
    </scm>
    
    <plugin>
      <groupId>org.apache.maven.plugins</groupId>
      <artifactId>maven-release-plugin</artifactId>
      <version>2.4.1</version>
    </plugin>
```
Since you used latest versions of other components, you can also use [latest version](http://maven.apache.org/plugins/index.html) of `maven-release-plugin` (where the 'Version' column meets the 'release' row).


### Eclipse
* In terminal, assuming you're still in `my-own-storm` dir, execute `mvn eclipse:eclipse`.
This will create eclipse related project files: `.project`, `.classpath` and `.settings` folder with some core preferences inside. After you open eclipse, if your `.jar` files are not listed under `Referenced Libraries`, refresh your workspace or restart eclipse.

### Play with Storm (finally)
* Next, from your **`repos`** directory execute `git clone https://github.com/nathanmarz/storm-starter.git`
That will clone Nathan Marz's [storm-starter project](https://github.com/nathanmarz/storm-starter) so you can finally start playing with Storm's spouts, bolts and topologies.
* You'll have to manually copy java files (that are needed for the demo you chose) from your freshly cloned `storm-starter` project into your own-storm's `src/main/java/...` folder.  
I suggest you start with BigData's HelloWorld - the [WordCount](https://github.com/mbonaci/mbo-storm/tree/master/src/main/java/hr/mbo/storm).

### Now go and whip up some awesome topologies...  
