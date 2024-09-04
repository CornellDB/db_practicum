[![Java CI with Gradle](https://github.com/CornellDB/db_practicum/actions/workflows/gradle.yml/badge.svg)](https://github.com/CornellDB/db_practicum/actions/workflows/gradle.yml)

# Cornell Database Systems Practicum - CS 4321/5321
The public repository for Cornell's Database Systems Implementation course (Practicum).

## Creating a Private Fork
You can create a private fork for your own convenience with the following steps.

**Clone**. First, you need to create a bare clone of the repository:

```
$ git clone --bare https://github.com/CornellDB/db_practicum.git db_practicum_tmp
$ cd db_practicum_tmp
```

**Create a Private Repository**. You can do that using the following [link](https://github.com/new). Pick up
a name of your preference in the *Repository Name* box. Below the description box, make sure to select the **Private**
option.

**Mirror-Push**. You now need to mirror the public DB Practicum repository.
```
$ git push --mirror https://github.com/YOUR_USERNAME/REPO_NAME.git
```

After this step, all the files of the public DB Practicum repository should have be cloned in your own private 
repository. You can now delete the `db_practicum_tmp` repo.

**Clone your Repo.** Clone your new private repository:
```
$ git clone https://github.com/YOUR_USERNAME/REPO_NAME.git
```

**Add Remote**. The next step is to add the official public repository as remote, to be able to fetch our commits for
the future deliverables.

```
$ cd REPO_NAME
$ git remote add upstream https://github.com/CornellDB/db_practicum.git
$ git remote set-url --push upstream DISABLE
```

**Check**. To check that the previous steps were successful, run the following command:
```
$ git remote -v
```

If you have done everything correctly, your output should look as the following:

```
origin	git@github.com:YOUR_USERNAME/REPO_NAME.git (fetch)
origin	git@github.com:YOUR_USERNAME/REPO_NAME.git (push)
upstream	https://github.com/CornellDB/db_practicum.git (fetch)
upstream	DISABLE (push)
```

**Sync-up**. In case we push new commits, you will need to sync-up your repo with the following command:
```
$ git fetch upstream
$ git rebase upstream/main
```

## General Instructions
### Java version
We will be using Java 21 for this assignment. Make sure to download and install the correct Java version, and set 
`JAVA_HOME` accordingly.
### Your first commit
Edit the `config.properties` file and set the `TEAM_NAME` part with your team's name. Next, set the `DELIVERABLE` 
variable to `1`. Keep in mind to update the latter in every deliverable, so the produced jars have the correct names.
For example, if you set your `TEAM_NAME` to `capybara` and `DELIVERABLE` to `3`, the `./gradlew build` command will 
produce a jar named `db_practicum_capybara-3.jar`.

### Keep the main branch clean
We strongly suggest to not push any commit/change to 
the `main` branch. This will make the sync-up with the official repo easier, when we push new changes that you will need
to fetch.

### One branch per deliverable
For every new deliverable, you will need to create a new branch. This will make it easier for you to keep track of your
changes across different deliverables, and will make debugging easier (i.e. a wrong output result or a performance 
degradation in an operator that used to work well in the past).

### Example
When you start, the default branch will be the `main`. You can verify that by typing:

```$ git branch```

For the first deliverable, create a new branch on top of the `main` branch as follows:

```$ git checkout -b p1_deliverable```

For the second deliverable, make sure you are on the `p1_deliverable` (you can check that using `git branch`). Create 
a new branch for the second deliverable on top of the `p1_deliverable` as follows:

```
$ git checkout -b p2_deliverable
```

Similar steps should be followed for each deliverable.

## Build Instructions
You can build the jars for your deliverable using the following command:

`$ ./gradlew build`

If that fails because of formatting issues, check the code formatting instructions below.

Make sure that your code runs by executing the following:

```java -jar ./build/libs/db_practicum_skeleton-1.0-SNAPSHOT.jar```

Unit tests run automatically when `./gradlew build`. If the tests cannot pass, the script will not produce 
the deliverable jar.

**If you try to run the `./gradlew build` command before implementing the deliverable, the command will fail because 
of the unit tests. This is completely normal, as you need to implement the required functionality to make the 
unit tests pass.**

## Code Formatting Instructions
We use the Google Java Format plugin in order to make sure that we keep a constant code formatting across assignments
of all teams.

### Check Formatting
To check if your code style complies, type the following:

`$ ./gradlew verGJF`

### Fix Format Issues
In case the previous step fails, you can auto-format your code as follows:

`$ ./gradlew goJF`
