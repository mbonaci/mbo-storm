# [Setting up a new Storm-based project](http://storm-project.net/)
This short (ok, relatively short) tutorial will guide you through the process of setting up fresh Twitter Storm project in your Eclipse IDE. You'll be using Maven to automate dependencies, Git to manage versions and GitHub to publish your source. Although Storm is written primarily in Clojure we'll be using Java API. This should be applicable to your OS, whether you're on Linux, Mac or Windows.

### A note about maven
I was really never much of a maven vorshiper, if I may say so. I use it because it's so widespread and it does its job (in the end) that I simply could not afford to ignore it. But there are better tools out there. E.g. a great ruby tool for Java development automation called [Apache Buildr](http://buildr.apache.org/) that's much simpler and easier to use. Unfortuantely we'll have to postpone covering it until it gains more traction in the community. I hope you'll take a look and ping me back with your comments. If you want to know more about this topic, take a look at [the great spymemcached author's blog post](http://dustin.github.io/2010/04/01/why-not-maven.html). Or [grails author's post](http://graemerocher.blogspot.com/2008/01/why-grails-doesnt-use-maven.html). If not, move on, your loss.

## Relatively quick start
Follow the steps bellow to start this bitch from the ground up:

### Prereqs:
I wont waste your time describing how the set up the basics. I'll assume you either already have these or that you'll be able to manage the basic setup yourself.
* [Install latest Java (JRE or JDK)](http://www.oracle.com/technetwork/java/javase/downloads/index.html).
* [Install latest Git](http://git-scm.com/downloads).
* [Install latest Maven](http://maven.apache.org/download.html).
* [Install latest Eclipse](http://www.eclipse.org/downloads).
* [Create <del>latest</del> GitHub account](http://github.com).

[Here](http://rogerdudler.github.io/git-guide/) is a nice, simple and good looking getting started with git.  
Instead of using GitHub, you can create your own local git "remote" repository like [this]().  

To check whether maven and git are correctly set up, open terminal (`cmd` on win) and type `mvn -version` and hit enter. Then `git --version`. Errors? Tough luck. Could be million different things. Use [StackOverflow](http://stackoverflow.com/).


### OK, let's kick some maven ass:
Now we're ready to go: 
* If you're already using git, you can step over this one. Otherwise, create a fresh folder that'll end up being the parent folder of our project's root folder. I'll call mine `repos`.
* In terminal, `cd` to your new `repos` or *whatever-you-called-it* dir and type `mvn archetype:generate`. We use this command to tell maven to (please) go and kick off our new project (create the weird pom.xml file that's known to sometimes smell like tomatoes).
* You'll be bombarded with the huge list of maven archetypes. Those are project templates that are used to automatically scaffold your new project.  

<small>Feel free to poke arond the list by typing a couple of interesting words and hitting enter, like Wicket, Lucene, Solr,...  
Please stop fooling around, quickly, without entering any text hit enter and you'll get the initial listing (yes, the huge one).</small>  
To choose default archetype, `maven-archetype-quickstart` just hit enter or type 292 (currently 292, but if you've got a different number use that one).
* Next, you're asked to choose version of `maven-archetype-quickstart`. Hit Enter to select the default, latest stable version (currently option 6, v1.1).
* Now maven wants to know `groupId`. What a hell is groupId, you ask? That's like a package directory. Like `org.apache` in `org.apache.maven` or `com.your-company` or `countryLocale.yourNickname`, whatever. But be prepared that your project's directory structure will be based on this input. E.g. if you type `my.friend.is.a.seal` you'll end up with a dir `repos/project-name/src/main/java/my/friend/is/a/seal`, so be careful.
* `artifactId`? That's the name of your project. Of course they couldn't name it "projectId", what's wrong with you? I'll use `my-little-storm`.
* Next, maven wants to know your project's version. Type `8.92.461`. I'm kidding, just hit enter (although that would be a better guess than `1.0-SNAPSHOT`. BTW, if a version contains “-SNAPSHOT”, then maven replaces it with a UTF datetime value at the time of installation or release. That's used to declare to the world that your project is currently under development.).
* Next, `package`. We wont go into that. Hit enter.
* Maven now presents you with the choices you made and asks your confirmation. Type `Y` and hit enter.
* `BUILD SUCCESSFUL` no way, you did it! If you examine your newly created project (artefactId/my-own-storm) folder you'll see all the dirs and files that `maven-archetype-quickstart` created for us. That's the standard maven project folder structure. Not much, I know, but we don't need fancy stuff, quickstart is just fine and simple, one main class and one test class. 
* To check if you can succesfuly build your new, still rather empty project, `cd` to `my-own-storm` and execute `mvn install`. You should see `BUILD SUCCESSFUL` somewhere in your terminal. If you now take a look at project's root, you'll se that this step created `target` folder. That's like your project's output folder with compiled java files and some other irrelevant stuff.  

If you encounter some weird error messages, like `use -source 5 or higher to enable static import declarations`, add this to your pom.xml located at project's root (```<build>``` tag should go at the bottom, just above, ```</project>``` tag, like in the next snippet):

```xml
        ...
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

So far, we have created our new `my-own-storm` project with Storm nowhere to be seen. Next, we're gonna address that by adding Storm dependencies to our `pom.xml`. Dependencies are used by maven to download additional libraries. When you get used to maven, I promise you, you'll never look back (which doesn't mean you wont look ahead). 
* Open `pom.xml` from your project's root in your favorite text editor (not yet Eclipse) and add this repository and dependency definitions:
```xml
    <repositories> 
      <repository> 
        <id>clojars.org</id> 
        <url>http://clojars.org/repo</url> 
      </repository> 
    </repositories> 
  https://github.com/mbonaci/mbo-storm-copy/settings
    <dependency>
      <groupId>storm</groupId>
      <artifactId>storm</artifactId>
      <version>0.8.2</version>
      <scope>compile</scope>
    </dependency>
```
[Here is the gist of the complete pom.xml](https://gist.github.com/mbonaci/5996278) so you can see where exactly those parts go.
[And here is the actual version of maven setup](https://github.com/nathanmarz/storm/wiki/Maven), maintained by Storm's father, Nathan Marz.
* Now, after we added Storm to our maven configuration, `cd` to your project's root and execute `mvn clean install`. I unnecessarily added `clean` just to spice things up. It deletes the `target` dir so maven can do fresh install. `BUILD SUCCESSFUL` again? You're a regular genious!

### Now the fun stuff, Git me up Scotty:
This is what the fans around the world have been waitning for. Five rounds for the undisputed... Ignore me, please.  
Git is the undisputed scm champion, let's sort out that part of our setup.
* Log in to your github account and create new `my-own-storm` repository. We'll add `README.md` later.  

You can use `https` to communicate with GitHub, but I suggest you [set up SSH](https://help.github.com/articles/generating-ssh-keys) in your local git installation and add it to your GitHub profile.  

* In your teminal, `cd` to `my-own-storm` dir and execute the following set of commands:

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

Now, if you open your new GitHub repository, you should see your files neatly stashed there.



