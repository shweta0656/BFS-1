/*
Time Complexity: O(n), we are processing all the nodes

Space Complexity: O(h) stack space, result is not included in space complexity

Worked in Leetcode: Yes

DFS
*/
import java.util.ArrayList;
import java.util.List;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class BinaryTreeLevelOrderTraversalDFS
{
    List<List<Integer>> result;
    public List<List<Integer>> levelOrder(TreeNode root)
    {
        if(root == null) return new ArrayList<>();
        this.result = new ArrayList<>();
        helper(root, 0);
        return result;
    }

    private void helper(TreeNode root, int depth)
    {
        //base case
        if(root == null) return;

        //logic

        //This is to find out if the list exist at every depth, if not create new list and add element
        if(result.size() == depth) {
            result.add(new ArrayList<>());
        }

        //if the list exist, get the depth and add the element to the list
        result.get(depth).add(root.val);

        helper(root.left, depth+1);
        helper(root.right, depth+1);
    }
}