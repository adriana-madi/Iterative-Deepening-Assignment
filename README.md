# Iterative-Deepening-Assignment

Assignment was set for the Introduction to Intelligent Systems class by A.King at the University of Kent 2019

Problem:
A map is given with 15 rectangles placed randomly amongst the plane. You are also given 11x2 random points on the plane describing a start and end destination. The scope of this assignment is to complete an algorithm that finds the shortest path between the two given points, without crossing through any of the rectangles in the path. 

Approach:
After creating hashmap data set, I decided to use expand an iterative deepening algorithm in order to find the shortest route. This is the most effective method (as opposed to bredth-frist search or Dijkstra's Algorithm) as it has better space behavior. An A* search would also have not been appropriate as this assignment focuses on distance being defined as amount of points visited, and not the actual physical distance between the points.
