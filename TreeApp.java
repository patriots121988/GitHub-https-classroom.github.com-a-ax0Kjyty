package edu.monmouth.tree;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.*;

public class TreeApp {
    public static void main(String[] args) {

        if(args.length!=1){
            System.err.println("Enter valid filename ");
            System.out.println("Could not locate file ");
            System.exit(-1);
        }
        Properties props = new Properties();
        final String LOGFILENAME = args[0];

        try{
            props.load(new FileInputStream(args[0]));
        } catch (IOException e){
            e.printStackTrace();
        }

        // list all properties to System.out
        props.list(System.out);

        // obtain the value of log_file_name property
        String nameProp = (props.getProperty("log_file_name"));
        System.out.println(nameProp);

        // obtain the value of node_values property
        String nodeValues = (props.getProperty("node_values"));
        System.out.println(nodeValues);

        // create an instance of the Tree class
        Tree tree = new Tree();

        // parse the key / value pairs of node_values and insert into tree
        String[] splitValues = nodeValues.split(";");
        for (String pair : splitValues){
            String[] splitPairs = pair.split(",");
            tree.insert(Integer.parseInt(splitPairs[0]), Double.parseDouble(splitPairs[1]));
        }

        if (tree.find(10)== null){
            System.out.println("Value does not exist in tree ");
        } else {
            System.out.println("Value exists in tree ");
        }

        // print out the keys using inorder
        tree.traverse(2);

        // determine if the value 12 is in the tree
        if(tree.find(12) != null) {
            System.out.println("key 12 is there " + tree.find(12));
        }
        else
        {
            System.out.println("key 12 is not there");
        }

        // determine if the value 80 is in the tree
        if(tree.find(80) != null) {
            System.out.println("key 80 is there " + tree.find(80));
        }
        else
        {
            System.out.println("key 80 is not there");
        }

        // print out the minimum key in the tree
        System.out.println("Min value in tree " + tree.min());

        // print out the maximum key in the tree
        System.out.println("Max value in tree " + tree.max());

    }
}