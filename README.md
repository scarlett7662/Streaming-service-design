### This is a Java-based subscription management system, incorporating role-based access control for secure user authentication. 
#### Streaming Wars Project
##### Introduction
Welcome to the Streaming Wars Project, a comprehensive system designed to manage and analyze the dynamics of streaming services, demographic groups, and their interactions with movies and Pay-Per-View (PPV) events. This system provides a framework for users to create and manage various entities within the streaming industry while tracking financial metrics and user engagement.

##### Key Features
Time Initialization: The system initializes with the current month set to October 2020, providing a clear starting point for the simulation of streaming activities.

Entity Creation: Users can create demographic groups, streaming services, movies, PPV events, and studios, allowing for a rich and varied environment to explore the streaming landscape.

Service Offerings: Streaming services can offer both movies and PPV events to users, enabling diverse content consumption options.

Demographic Engagement: The system allows portions of demographic groups to view offerings from various streaming services, facilitating targeted marketing and engagement strategies.

Time Advancement: Users can advance the time period to the next month, simulating the ongoing nature of the streaming market and its evolving trends.

Financial Tracking: The system calculates and displays the monetary transactions involved, including:

Current Month Spending: Total monies spent by demographic groups in the current month.

Previous Month Analysis: Financial insights from the previous month.

Cumulative Totals: Overall financial metrics since the program's inception, allowing for long-term analysis of trends and performance.
##### Purpose
The Streaming Wars Project aims to provide a detailed simulation of the streaming service industry, enabling users to understand the financial dynamics and user interactions within this rapidly evolving market. By leveraging this system, users can gain insights into spending behaviors, service offerings, and the overall economic impact of streaming platforms.
##### Here is the UML class diagram for this system. 

![pic](https://github.com/scarlett7662/Streaming-service-design/blob/main/class_diagram.jpg)

# To Install Docker go to:
```
https://docs.docker.com/get-docker/
```

# Note please run all scripts from the project root directory

### To build:

```
docker build -t gatech/streamingwars -f Dockerfile ./
```

### To copy your jar to host:
#### Mac / Linux
```
./scripts/copy.sh
```
#### Windows
```
.\scripts\copy.sh
```

### To test a specific scenario against the initial jar
#### Mac / Linux
```
./scripts/test.sh <scenario>
```
#### Windows
```
.\scripts\test.sh <scenario>
```

### To run in interactive mode
#### Step 1 from the host 
```
docker run -ti gatech/streamingwars sh
```
#### Step 2 from the container
```
java -jar streaming_wars.jar
```
#### Step 3 from the jar
* From there you can run any of the commands listed from the assignment:
```
create_demo,<short name>,<long name>,<number of accounts>
create_studio,<short name>,<long name>
create_event,<type>,<name>,<year produced>,<duration>,<studio>,<license fee>
create_stream,<short name>,<long name>,<subscription price>
offer_movie,<streaming service>,<movie name>
offer_ppv,<streaming service>,<pay-per-view name>,<price>
watch_event,<demographic group>,<percentage>,<streaming service>,<event name>
next_month
display_demo,<short name>
display_stream,<short name>
display_studio,<short name>
display_events
display_offers
display_time
stop
```

### To run & test in interactive mode

```
java -jar streaming_wars.jar < commands_0.txt > stream_test_0_results.txt
java -jar streaming_wars_initial.jar < commands_0.txt > stream_test_initial_0_results.txt
diff -s stream_test_0_results.txt stream_test_initial_0_results.txt > results_0.txt
```

### To run a specific scenario with your jar and output to localhost

```
docker run gatech/streamingwars java -jar streaming_wars.jar < commands_0.txt > stream_test_0_results.txt
```

### If you get stuck in an infinite loop
Simply stop and remove the running container
```
docker ps
docker rm -f <container_id>
```
