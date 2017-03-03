David Ciupei
ciupei_david@yahoo.com

     In this project we had a Frequency table with the methods click and count. click would either increment the count of the key if the key is already in the table or insert the key and give it a count of 1. Count returns the count of the key if it is not int the table 0 is returned. If the table were to get to full the table would reach the maximum load factor the table is then doubled and rehashed. In this project we also used quadratic probing to fix collisions or clusters that happens in hashtables. It will examine every slot as long as the size of the table is a power of two.

	As for the test applications. The words in Hamlet will be taken and passed in the table through the method click where each word will be put in the table using quadratic probing to fix any colisions along the way. After the elements are put in the table the method dump will print out null if there is no key but if there is a key it will print out the key and the count associated with it. The SortedWordCount test will take a look at the words of the moby dick txt file and find the top 10 words in the file. Each time a word is repeated the count of the word is incremented. 

	 	