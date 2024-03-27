

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

### Apriori
First we take as input from the user the filename, minimum support value and the confidence.
Support: Fraction of transactions that contain an itemset
Frequent Itemset: An itemset whose support is gerater than or equal to a minsup threshold

Support of a rule (s) X -> Y : Fraction of transactions that contain both X and Y.
Confidence of a rule(c) : Measures how often items in Y appear in transactions that contain X 
Confidence => Support (X union Y)/Support(X)

Given a set of transactions T, the goal of association rule mining is to find all rules having 
support ≥ minsup threshold
confidence ≥ minconf threshold

Rules originating from the same itemset have identical support but can have different confidence
Two-step approach: 
1) Frequent Itemset Generation
	Generate all itemsets whose support >= minsup
2) Rule Generation
	Generate high confidence rules from each frequent itemset, where each rule is a binary partitioning of a frequent itemset

Apriori principle (Main observation):
If an itemset is frequent, then all of its subsets must also be frequent
The support of an itemset never exceeds the support of its subsets
This is known as the anti-monotone property of support acting on the subsets of the itemsets.

<img width="666" alt="image" src="https://github.com/smit-1999/Apriori-FP-Tree/assets/44575416/89bc3001-1c3f-4c8f-95d6-88895d23c4d5">
<img width="740" alt="image" src="https://github.com/smit-1999/Apriori-FP-Tree/assets/44575416/c61b4b59-574b-4469-a67d-9c086e1e6159">
<img width="744" alt="image" src="https://github.com/smit-1999/Apriori-FP-Tree/assets/44575416/87135f28-06e5-426f-8a6c-7fa4c207b8b8">

Once we know what the frequent itemsets are, we proceed to rule generation for these frequent itemsets. 
If {A,B,C,D} is a frequent itemset, candidate rules:
ABC->D, ABD->C, ACD->B, BCD->A, A->BCD,	B ->ACD, C ->ABD,D ->ABC,AB->CD,AC ->BD, AD->BC, BC->AD,BD ->AC, CD->AB

### FP Tree:


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

