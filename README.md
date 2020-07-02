

# Table of Contents

  * [Summary](#summary)
  * [Algorithm](#algorithm)
  * [Setup](#setup)
  * [Authors](#authors)  
  * [Contribution](#contribution)

## Summary
This is a part of the *CS F415 Data Mining* Course at BITS Pilani Hyderabad Campus. In this assignment, frequent itemsets and interesting association rules are generated using Apriori and FP-growth algorithm.

**So,what are these association rules?**


Let us first understand what an itemset is.
An itemset is a collection of one or more items.
For example :
 - {Milk, Bread, Eggs}
 - {Beer, Yoghurt}
 - {Chocolates}
 
All these are itemsets. A k-itemset contains k items.

So,association rules are expressions of the form 
 X->Y where X and Y are itemsets.
 
Example of association rule is *{Milk, Diaper} -> {Eggs,Cream}*

**How can these rules be interesting?** 

Grocery stores process thousands of transactions each week.Walmart processes some 200 million transactions on a weekly basis. Such huge data can help us establish and reveal interesting consumer buying patterns. 

If I as a store manager analyse my transactions and find out that in every 100 customers there are 45 people who  buy milk later buy eggs as well.Or there are 60 customers always buying coke along with chips and biscuits.

This  can help me (a store owner)to group items and place them nearby. This regrouping can increase sales and be beneficial to the company. Hence these rules are interesting to analyse. 

## Algorithm


## Setup
The project is built entirely in Java.
For directly running the code
 - Download [NetbeansIDE](https://netbeans.org/downloads/6.1/index.html) basic version
- Clone this repository into your local system.
- Open the folder Apriori / FP-tree in Netbeans IDE.
- Build and run it.

On running the project, an input file will be asked. I have added a sample groceries.csv file in the dataset folder.
You can directly type in 

  
	
*groceries.csv* [enter] 
*supportValue* [enter] 
*confidence* [enter]

to run the project.

If you wish to add or use your own file please create it in the same format as *groceries.csv*.
Support value and Confidence values should be between 1 to 100 inclusive.


## Authors

 - [**Smit Shah**](https://github.com/smit-1999/)

## Contribution

