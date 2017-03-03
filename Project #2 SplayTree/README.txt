{\rtf1\ansi\ansicpg1252\cocoartf1265\cocoasubrtf210
{\fonttbl\f0\fswiss\fcharset0 Helvetica;}
{\colortbl;\red255\green255\blue255;}
\margl1440\margr1440\vieww10800\viewh8400\viewkind0
\pard\tx720\tx1440\tx2160\tx2880\tx3600\tx4320\tx5040\tx5760\tx6480\tx7200\tx7920\tx8640\pardirnatural

\f0\fs24 \cf0 David Ciupei\
CS223 \
Project #2\
\
- splay trees is basically a binary search tree. But it adjusts its self when searching for an element. When you insert into the tree the element will be inserted at the bottom and splay it\'92s way to the root. Splay trees make it easy for when you are searching for an element because the element or elements you are searching for are at the root or near the root. In the project we also keep track of the rotations and comparisons and use memoization to make the splay tree faster. \
\
David Ciupei \
- email: Ciupei_david@yahoo.com\
\
Instructor Wayne O. Cochran\
- email: wcochran@vancouver.wsu.edu\
\
- When running the code with the binomial class n is set to 50 and with K being n/2 the time it took to compile is ~0.0127 seconds. It had 5280 comparisons and 4535 rotations. The program is faster with memoization versus without. \
\
- Binomial.java\
- SplaySymbolTable.java\
- SplaySymbolTableUnitTest.java\
- SymbolTable.java\
- TestSplaySymbolTable.java\
- TreePrinter.java\
\
- When running the main test harness a series of tests will be applied to the code. Strings or numbers will be put in the tree making sure it works. When inserting or searching for numbers or strings they should be at the root or near the root of the tree. Printing the splay tree helps show how the tree looks after inserting into the tree or searching in the tree.  }