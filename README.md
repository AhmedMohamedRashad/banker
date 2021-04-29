# banker
Operating System Concepts
deadlock avoidance ( banker's algorithm which Multiple instances of a resource )
The deadlock-avoidance algorithm dynamically examines the resource-allocation state to ensure that there can never be a circular-wait condition.
If a system is in safe state then no deadlocks.
If a system is in unsafe state then possibility of deadlock.
            Structures for the Banker’s Algorithm
Let n = number of processes, and m = number of resources types.
Available: Vector of length m. If available [j] = k, there are k instances of resource type Rj available
Max: n x m matrix. If Max [i, j] = k, then process Pi may request at most k instances of resource type Rj
Allocation: n x m matrix. If Allocation[i, j] = k then Pi is currently allocated k instances of Rj
Need: n x m matrix. If Need[i, j] = k, then Pi may need k more instances of Rj to complete its task
Need [i, j] = Max[i, j] – Allocation [i, j]
