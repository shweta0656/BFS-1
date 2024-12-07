import java.util.HashMap;
import java.util.List;
import java.util.*;

/*
Topological Sort Using Kahn's Algorithm

Here, n be the number of courses and m be the size of prerequisites.

Time complexity: O(m+n)

Initializing the adj list takes O(m) time as we go through all the edges. The indegree array take O(n) time.
Each queue operation takes O(1) time, and a single node will be pushed once, leading to O(n) operations for n nodes.
We iterate over the neighbors of each node that is popped out of the queue iterating over all the edges once.
Since there are total of m edges, it would take O(m) time to iterate over the edges.

Space complexity: O(m+n)

The adj arrays takes O(m) space. The indegree array takes O(n) space.
The queue can have no more than n elements in the worst-case scenario. It would take up O(n) space in that case.

Topological Sort: Identifying a cycle in the graph using BFS

In graph theory, an indegrees array is an array that contains the number of edges that come into
each vertex in a directed graph

Outdegree => The number of edges that leave a vertex in a directed graph

Total degree => The sum of the indegree and outdegree of a vertex

A vertex with an indegree of zero is called a source, and a vertex with an outdegree of zero is
called a sink. The sum of all the indegrees in a directed graph is equal to the total number of
edges in the graph.
*/
class CourseSchedule {
        public boolean canFinish(int numCourses, int[][] prerequisites) {

            int[] indegrees = new int[numCourses];
            HashMap<Integer, List<Integer>> map = new HashMap<>(); //adjacency list

            //O(Edges) in graph
            for(int[] pr : prerequisites) //go over prerequisites array to fill indegrees array and adjacency map
            {
            /*
                [(), ()...] basically it is (dependent, independent) , (dependent, independent)
                pr[0] dependent
                eg: (1,0), (2,1) .. pr[0]=1, pr[1]=0 ; pr[0]=2, pr[1]=1

                pr[1] independent
            */
                indegrees[pr[0]]++; //indegrees array to store the count of dependent courses and increment them if found again

            /*
                adjacency matrix {Hash Map}
                independent : dependent
                key         : value
            */
                if(!map.containsKey(pr[1])) {
                    map.put(pr[1], new ArrayList<>());
                }
                map.get(pr[1]).add(pr[0]);
            }

            int count = 0;
            //O(Vertices) in graph
            //Iterate over indegrees array and put all independent nodes in the queue
            Queue<Integer> q = new LinkedList<>();
            for(int i=0; i<numCourses; i++)
            {
                if(indegrees[i] == 0) {//independent nodes
                    q.add(i);
                    count++;
                }
            }

            /*O(Vertices + Edges)
                All the list in adjacency map, form the edges, for all vertices we are traveling over all the edges
            /*
                Queue can be empty at the initial stage itself. That implies all the courses have dependencies
                and none of the courses went inside the queue. At last as well, if queue becomes empty and count
                is not equal to num of courses, then there is a cycle.
             */
            while(!q.isEmpty())//if we have nodes inside the queue, we have to process it till the queue becomes empty,
            {
                int curr = q.poll(); //pop the independent courses
                List<Integer> dependents = map.get(curr); //now look at the dependent courses on the indepedent courses from adjacency map

                if(dependents != null) //if we have dependents courses
                {
                    for(int dependent : dependents) //go over each and every dependent course and decrement indegrees by 1
                    {
                        indegrees[dependent]--;
                        if(indegrees[dependent] == 0) {
                            q.add(dependent); //add the independent course to queue
                            count++;
                        }
                    }
                }
            }

            if(count == numCourses) return true; //all courses are independent

            return false;
        }
    }