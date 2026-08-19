# Shortest Route Map

A Java program that computes the least-expensive routes from a starting city to every other reachable city in a network, using Dijkstra's algorithm. Built for CUS1156: Software Design Methods (St. John's University).

## Overview

The program reads a text file of city connections in the form:

```
city1 city2 distance
```

Connections are treated as bidirectional (Pendleton–Peoria costing 8 means Peoria–Pendleton costs 8 too). The starting city is the first city named in the file. The program then computes and prints the shortest distance from that starting city to every other city in the network.

For example, given the sample data, the least expensive route from Pendleton to Peoria costs 8 (routing through Pierre and Pueblo).

`DistanceFinder` and `DistanceFinderDemo` were completed as part of the assignment. `DistanceTo` was provided starter code and left unmodified, as required.

## Project Structure

```
map-project/
├── src/
│   ├── module-info.java         # Java module descriptor
│   └── map/
│       ├── DistanceFinder.java      # Builds the graph and runs Dijkstra's algorithm
│       ├── DistanceFinderDemo.java  # Entry point (course-provided, unmodified)
│       └── DistanceTo.java          # City/distance pair, Comparable by distance (course-provided, unmodified)
├── cities1.txt                  # Sample city network
├── cities2.txt                  # Sample city network (same graph, different line order)
├── cities3.txt                  # Sample city network (same graph, different line order)
├── cities4.txt                  # Sample city network (same graph, different line order)
└── README.md
```

## Class Design

- **`DistanceTo`** — pairs a target city with a distance; implements `Comparable` by distance so it can be used directly in a `PriorityQueue`.
- **`DistanceFinder`** — reads a city-connections file into a `Map<String, HashSet<DistanceTo>>` adjacency list, then computes shortest distances from the starting city using Dijkstra's algorithm with a `PriorityQueue` as the min-frontier and a `TreeMap` to record finalized distances.
- **`DistanceFinderDemo`** — prompts for a filename, runs `DistanceFinder`, and prints the starting city plus the shortest distance to every reachable city.

## Running It

Requires a JDK with module support (Java 9+), since this is set up as a Java module (`mapProjectO`).

```bash
cd src
javac -d ../bin module-info.java map/*.java
java --module-path ../bin --module mapProjectO/map.DistanceFinderDemo
```

When prompted, enter one of the sample filenames (e.g. `../cities1.txt`, adjusting the path relative to where you run the program).

## Author

Ayman Mohammed
