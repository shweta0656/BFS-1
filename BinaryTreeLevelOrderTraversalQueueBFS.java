/*
Time Complexity: O(n), we are processing all the nodes

Space Complexity: O(n/2) => O(n)

Worked in Leetcode: Yes

BFS => FIFO => Queue
*/

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//Definition for a binary tree node.
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

/*
First, we add the root to queue, check the size of the queue to find elements at a particular level, then we pop
the queue and add the element in temp arraylist which will be storing the elements at each level. After we add
the element which was popped to a temp list, we need to find out if the popped-out element has left and right babies.
If it does, we will add it in the queue, this will not impact our level list distinction as we already have the
size of the queue to determine how much we need to traverse to pop the element and store in a temp list. This is why
we have "for loop" to track the elements are particular level. Finally, when we are able to traverse the complete
tree, the q will be empty in the end. At every level once the elements at that level are added to the temp list, we
are storing in the result as a temp list will be a new object after each level.
 */

class BinaryTreeLevelOrderTraversalQueueBFS {
    public List<List<Integer>> levelOrder(TreeNode root)
    {
        //base case
        if(root == null) return new ArrayList<>();

        List<List<Integer>> result = new ArrayList<>();

        /*
        If we create Queue using Arraylist, queue property is FIFO and in arraylist when we take the first element out,
        we have to shift all the elements one place towards a left, so time complexity will be O(n) for each and every
        element.
         */
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        while(!q.isEmpty())
        {
            LinkedList<Integer> temp = new LinkedList<>();
            int size = q.size(); //distinction between levels

            for(int i=0; i<size; i++)
            {
                TreeNode curr = q.poll();
                temp.add(curr.val);
                if(curr.left != null) {
                    q.add(curr.left);
                }
                if(curr.right != null) {
                    q.add(curr.right);
                }
            }

            result.add(temp);
        }

        return result;
    }
}