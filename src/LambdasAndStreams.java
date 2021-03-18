/*
  Author: MeadowTsunami Softworks LLC (USA)
  Date:   03/18/2021
  README: This project is meant to serve as an area of reference and practice for COMMMON STREAM + LAMBDA OPERATIONS IN JAVA.
	  Assumes knowledge over Collections, OOP, loops/logic.  Please cover these first if you are new to lambdas.
          Instead of relying on the Oracle documentation, which can sometimes be lacking in examples, common
          examples that you NEED TO KNOW are shown here.  This guide should STREAMLINE you (pun intended) into knowledge on streams.
                    
IMPORTS: Please review these Collections before continuing with the tutorial, if you are not confident on these.  */         
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator; //<-- Definitely research and understand Comparator first if you have never created a Comparator.
import java.util.Hashtable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Supplier;
import java.util.stream.*;
import java.util.Collections;

public class LambdasAndStreams { 
	public static void main(String[] args) {
		
            //Print out Java Version: Note, you need java 1.8+ to use Stream/Lambdas!
	    System.out.println("Your Java version: ");
	    System.out.println(System.getProperty("java.version"));
	    System.out.println(System.getProperty("java.specification.version"));
		
	    //----------- ARRAYS: Use Streams and Stream API Methods around ARRAYS---- ----------------
	    //Primitive arrays, List, ArrayList and how to use the common streams API methods around each.

	    //Count of the amount of elements in a primitive int[]
	    int[] orig_int_arr = new int[] {100,99,98,1,2,3,4,5,6,7,8,9,10,1,3,5,7,9,2,4,6,8,10,97,96};
	    int count = (int) Arrays.stream(orig_int_arr).count(); //Count returns a long, so make sure to cast.
	    System.out.println("Number of elements in the array: " + count);
	    //(Note: Yes, of course you can just use .length or .size() in Java, but the point is to use streams.)
	    
		//Minimum value of a primitive int[]: 
		int min = Arrays.stream(orig_int_arr).min().orElse(-1); //min() returns an Optional, so must use orElse and give a default value.
		System.out.println("Find min of a primitive int[]: " + min);
		
		//Maximum value of a primitive int[]: 
		int max = Arrays.stream(orig_int_arr).max().orElse(-1); 
		System.out.println("Find max of a primitive int[]: " + max);
		
		//Minimum value of a List<Integer>: 
		List<Integer> orig_list = List.of(1,2,3,4,5,6,7,8,9,10,9,8,7,6,5,4,3,2,1,100,1000);
		int min2 = orig_list.stream().max(Integer::compareTo).orElse(-1);
		System.out.println("Find min of List<Integer>: " + min2);
		
		//Maximum value of a List<Integer> using a custom comparator: Shows lambda + ternary syntax for a Comparator
		int max2 = orig_list.stream().max((x,y) -> x < y ? 1 : -1).orElse(-1);
		System.out.println("Find max of a List<Integer>: " + max2);
		
		//Minimum value of an ArrayList<Integer>: This time, pass in a functional interface which matches Comparator
		int min3 = orig_list.stream().min(Integer::compareTo).orElse(-1);;
		System.out.println("Find min of an ArrayList<Integer>: " + min3);
		
		//Maximum value of an ArrayList<Integer>:
		ArrayList<Integer> orig_AList = new ArrayList<Integer>(List.of(5,9,100,50,6,4,5,7,2,3,5,6,4,4,5,6,433,2));
		int max3 = orig_AList.stream().max(Integer::compareTo).orElse(-1);
		System.out.println("Find max of an ArrayList<Integer>: " + max3);
		
		//CONVERT a primitive int[] to a List<Integer>: Very useful thing to do!
		int[] prim_array = new int[] {12,13,14,150,100,50,15,17,1,2,3,4,4,5,6,7};
		List<Integer> converted1 = Arrays.stream(prim_array).boxed().collect(Collectors.toList());
		System.out.println("Converted primitive int[] to regular List<Integer>: " + converted1);
		
		//CONVERT a primitive int[] to an ArrayList<Integer>:
		ArrayList<Integer> converted2 = Arrays.stream(prim_array).boxed().collect(Collectors.toCollection(ArrayList::new));
		System.out.println("CONVERT a primitive int[] to an ArrayList<Integer>: " + converted2);
		
		//CONVERT a List<Integer> to a primitive int[]:
		List<Integer> IntegerList = new ArrayList<Integer>(List.of(1,2,3,4,5,6,7,8,9,10,55,57,50,100));
		int[] prim_list = new int[] {};
		prim_list = IntegerList.stream().mapToInt(v -> v).toArray();
		System.out.println("Convert List<Integer> to a primitive int[]: " + Arrays.toString(prim_list));
		
		//CONVERT an ArrayList<Integer> to a primitive int[]:
		ArrayList<Integer> al_integer = new ArrayList<Integer>(List.of(1,2,3,4,5,6,7,8,9,10));
		int[] prim_integer_arr = al_integer.stream().mapToInt(v -> v).toArray();
		System.out.println("Convert ArrayList<Integer> to a primitive int[]: " + Arrays.toString(prim_integer_arr));
		
		//REMOVE all duplicates from a primitive int[]:
		prim_list = Arrays.stream(prim_list).distinct().toArray();
		System.out.println("Remove all duplicates from a primitive int[]: " + Arrays.toString(prim_list));
		
		//REMOVE all duplicates from a List<Integer>:
		al_integer = al_integer.stream().distinct().collect(Collectors.toCollection(ArrayList::new));
		System.out.println("Remove all duplicates from a List<Integer>: " + al_integer);
		
		//REMOVE all duplicates from an ArrayList<Integer>:
		ArrayList<Integer> al_with_many_dupes = new ArrayList<Integer>(List.of(1,1,2,2,3,3,4,4,5,5,6,6,7,7,8,8,9,9));
		al_with_many_dupes = al_with_many_dupes.stream().distinct().collect(Collectors.toCollection(ArrayList::new));
		System.out.println("Remove all duplicates from a primitive int[]: " + al_with_many_dupes);
		
		//Multiply all elements in a primitive int[] by 2:
		int[] prim_int_arr = new int[] {2,3,4,5};
		prim_int_arr = Arrays.stream(prim_int_arr).map(v -> v*2).toArray();
		System.out.println("Multiply all elements in a primitive int[] by 2: " + Arrays.toString(prim_int_arr));
		
		//Multiply all elements in a List<Integer> by 2: 
		List<Integer> list_of_int = new ArrayList<Integer>(List.of(5,10,15,20));
		list_of_int = list_of_int.stream().map(v -> v*2).collect(Collectors.toCollection(ArrayList::new));
		System.out.println("Multiply all elements in a List<integer> by 2: " + list_of_int);
		
		//Multiply all elements in an ArrayList<Integer> by 2:
		ArrayList<Integer> elements = new ArrayList<Integer>(List.of(3,6,9,12,15));
		elements = elements.stream().map(v -> v*2).collect(Collectors.toCollection(ArrayList::new));
		System.out.println("Multiply all elements in an ArrayList<Integer< by 2: " + elements);
		
		//Filter out all elements in a primitive String[] < 'M' (keep only those lexicographically greater than 'M')
		//Hint: Convert to ArrayList<String> then use Predicate in filter.
		//Hint 2: Filter will always KEEP the elements which return true for the Predicate inside of the filter statement.
		String[] names = new String[] {"Alex","Jennifer","Tyrell","Abishek","Chen","Someone"};
		ArrayList<String> names_al = Arrays.stream(names).collect(Collectors.toCollection(ArrayList::new));
		names_al = names_al.stream().filter(str -> str.compareTo("M") > 0).collect(Collectors.toCollection(ArrayList::new));
		System.out.println("Filter out all elements less than 'M' in a primitive String[]: " + names_al);
		//Pitfalls: Make sure to use a capital letter if you are comparing lexicographically. 
		//          Otherwise, convert to lowercase first before comparison (we'll do that below)
		
		//Filter out all elements in a List<String> > 'M' (keep only those lexicographically coming before 'M'):
		ArrayList<String> names_al2 = Arrays.stream(names).collect(Collectors.toCollection(ArrayList::new));
		names_al2 = names_al2.stream().filter(str -> str.toLowerCase().compareTo("m") < 0).collect(Collectors.toCollection(ArrayList::new));
		System.out.println("Filter out all elements in a List<String> > 'm': " + names_al2);
		//Notice, with this example converted it toLowerCase() FIRST, then compared it to 'm'.  This is pretty common
		//because usually we don't worry about sorting for capitalized vs uncapitalized letters.  Memorize it! 
		
		//Filter out all elements out in an ArrayList<String> lexicographically less than or equal to 'M':
		//Hint: How can you EASILY take all the Strings less than or equal to 'M'?  What letter will it be?
		ArrayList<String> names2 = new ArrayList<String>(List.of("Alice","N","Nancy","Betheny","Christina","Thomas","Chris","Frank","Len","Maddie","Zelda"));
		names2 = names2.stream().filter(str -> str.toLowerCase().compareTo("n") >= 0).collect(Collectors.toCollection(ArrayList::new));
		System.out.println("Filter out all elements in an ArrayList<String> less than 'm': " + names2);
		//Pitfalls: In order to filter out all those less OR EQUAL TO 'M', we can start with n.  This is an easy way to do it
		//          that doesn't require any special ASCII tricks or conversions to integer, etc...
		
		//Filter out all odds from an ArrayList<Integer>:
		ArrayList<Integer> someodds = new ArrayList<Integer>(List.of(1,2,4,3,11,5,7,9,12));
		someodds = someodds.stream().filter(num -> num%2==1).collect(Collectors.toCollection(ArrayList::new));
		System.out.println("Filter all elements that are odd from an ArrayList<Integer>: " + someodds);
		//Pitfalls: To filter out all odds, modulus 2 must be equal to 1.   
		
		//Filter out all evens from a List<Integer>: 
		ArrayList<Integer> someevens = new ArrayList<Integer>(List.of(2,4,6,8,10,11,7,5,3,1));
		someevens = someevens.stream().filter(n -> n%2 == 0).collect(Collectors.toCollection(ArrayList::new));
		System.out.println("Filter all even elements from a List<Integer>: " + someevens);
		
		//Filter out all evens from a primitive int[]: (Remove all evens)
		int[] prim_evens = new int[] {2,4,6,8,10,11,15,3,1};
		prim_evens = Arrays.stream(prim_evens).filter(n -> n%3 == 0).toArray();
		System.out.println("Filter all even elements from a primitive int[]: " + Arrays.toString(prim_evens));
		
		//Sort a primitive int[] from least to greatest: 
		int[] unsorted = new int[] {5,3,8,6,1,9,100,3,7,4,30,50};
		unsorted = Arrays.stream(unsorted).sorted().toArray();
		System.out.println("Sort a primitive int[] from least to greatest: " + Arrays.toString(unsorted));
		
		//Sort a List<Integer> from greatest to least:
		List<Integer> unsorted_list = new ArrayList<Integer>(List.of(5,1,3,100,50,30,15,25));
		unsorted_list = unsorted_list.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toCollection(ArrayList::new));
		System.out.println("Sort a List<Integer> from greatest to least: " + unsorted_list);
		//Pitfalls: You need to pass in a function match the Comparator functional interface into the sorted() method.
		//          However, since we don't want to make a Comparator just for reversing, use Comparator.reverseOrder()
		
		//Sort an ArrayList<Integer> from greatest to least:
		ArrayList<Integer> unsorted_al = new ArrayList<Integer>(List.of(5,4,3,2,1,2,3,4,5,6,7,8,9,10));
		unsorted_al = unsorted_al.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toCollection(ArrayList::new));
		System.out.println("Sort an ArrayList<Integer> from greatest to least: " + unsorted_al);
		
		//Sort a primitive String[] lexicographically:
		String[] prim_strings = new String[] {"def","abc","jik", "geh"};
		prim_strings = Arrays.stream(prim_strings).sorted().toArray(String[]::new); //*** See pitfalls
		System.out.println("Sort a primitive String[] alphanumerically: " + Arrays.toString(prim_strings));
		//Pitfalls: When iterating over a String[] using stream, an Object[] is returned.  This means you
		//          MUST convert it manually back to a String[] using String[]::new.  (Does not convert 
		//          back automatically like the examples above for int[].  Memorize this fact.)
		
		
		//Sort an ArrayList<String> in reverse lexicographic order (Z comes first)
		ArrayList<String> somestrings_al = new ArrayList<String>(List.of("Timothy","Michael","Angela","Suresh"));
		somestrings_al = somestrings_al.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toCollection(ArrayList::new));
		System.out.println("Sort an ArrayList<String> alphanumerically: " + somestrings_al);
		//Here we use the sorted() method which normally sorts in alphanumeric or lexicographic order.  Since we want Z first,
		//we use the Comparator.reverseOrder() method to pass into sorted's Comparator functional interface.
		
		//Sort a List<String> lexicographically: A to Z.  Must use a stream.
		List<String> somestrings_l = new ArrayList<String>(List.of("Mike","Tyson","Lesta","Cynthia"));
		somestrings_l = somestrings_l.stream().sorted().collect(Collectors.toList());
		System.out.println("Sort a List<String> alphanumerically: " + somestrings_l);
		
		//Find the Customer with the maximum age in a List<Customer>:
		List<Customer> custs = createCustomers(); //(See below for how to use List.of() for objects)
		                                          //Note: A customer CAN have a blank email (null), but is guaranteed
		                                          //to have a value for its other fields.  (This makes the email examples tricky)
		Customer def_customer = new Customer("Default Dude","1234567890",-1,"DefaultDude@Default.com");
		Customer max_age_cust = custs.stream().max((p1,p2) -> p1.getAge() > p2.getAge() ? 1 : -1).orElse(def_customer); 
		System.out.println("Find the Customer with maximum age in a List<Customer>: " + max_age_cust.getName() + " " + max_age_cust.getAge());
		//Pitfalls: A default object of the class must be passed into orElse()
		
		//Find the Customer with the minimum age in an ArrayList<Customer>:
		Customer min_age_customer = custs.stream().min(Comparator.comparing(Customer::getAge)).orElse(def_customer);
		System.out.println("Find the Customer with minimum age in ArrayList<Customer>: " + min_age_customer.getName() + " " + min_age_customer.getAge());
		//Pitfalls: We can either pass in a Lambda expression which matches a Comparator, or pass in 
		//          one of the Comparator methods into min().  Here, we used Comparator methods.
		
		//Find the Customer with the highest alphanumeric name in a List<Customer> using streams with a lambda Comparator:
		Customer max_name_cust = custs.stream().max((p1,p2) -> p1.getName().compareTo(p2.getName())).orElse(def_customer);
		System.out.println("Find Customer with the highest alphanumeric name in List<Customer>: " 
		                    + max_name_cust.getName() + " " + max_name_cust.getAge());
		//This time we made our own Comparator using a Lambda.  
		
		//Find the Customer with the lowest alphanumeric name in a List<Customer> using streams with a Comparator method.
		Customer min_name_cust = custs.stream().min(Comparator.comparing(Customer::getName)).orElse(def_customer);
		System.out.println("Find Customer with the lowest alphanumeric name in List<Customer>: " 
		                   + min_name_cust.getName() + " " + min_name_cust.getAge());
		
	    //Convert string[] to ArrayList<String> while ALSO removing duplicates (known as stream chaining):
	    String[] s = new String[] {"z","z","y","y","a","a","b","b","c","c"};
	    ArrayList<String> sl = 
	       Arrays.stream(s)
	      .distinct()
	      .collect(Collectors.toCollection(ArrayList::new));
	    System.out.println("Convert String[] to ArrayList<String> while also removing dupes" + sl);
	    
	    //Convert int[] to ArrayList<Integer> while also removing all duplicates using streams:
	    int[] many_dupes = new int[] {1,1,1,2,2,2,3,3,3,4,4,4,5,5,5};
	    List<Integer> no_more_dupes = Arrays.stream(many_dupes).boxed().distinct().collect(Collectors.toCollection(ArrayList::new));
	    System.out.println("Convert int[] to ArrayList<Integer> while also removing dupes: " + no_more_dupes);
	    
	    //Shuffle an ArrayList<Integer> using a stream.
	    ArrayList<Integer> make_me_random = new ArrayList<Integer>(List.of(1,2,3,4,5,6,7,8,9));
	    make_me_random = make_me_random.stream().mapToInt(v -> v).boxed().collect(Collectors.toCollection(ArrayList::new));
	    java.util.Collections.shuffle(make_me_random);
	    System.out.println("Shuffle an ArrayList<Integer> (randomize their indexes): " + make_me_random);
	    //Note: This example is mostly for fun and to show one more example on how to convert from IntStream back to ArrayList.
	    //      We didn't need to convert back and forth from a stream to an ArrayList, but the question asked for it so do so.
	    
	    //Reverse an ArrayList<String>: 
	    ArrayList<String> reverse_the_strings = new ArrayList<String>(List.of("Z","Y","X","B","A"));
	    Collections.reverse(reverse_the_strings);
	    System.out.println("Reverse the order of an ArrayList<String>: " + reverse_the_strings);
	    //Pitfalls: Don't use streams for doing a Reversal.  It is obtuse at best, and inefficient at least.
	    
	    //Filter out all Integers less than 50 (keep only greater than or equal to 50) in int[]:
	    int[] greater_than_50 = new int[] {51,52,53,54,55,50,49,48,47,46,45};
	    greater_than_50 = Arrays.stream(greater_than_50).filter(i -> i >= 50).toArray();
	    System.out.println("Filter out all Integers less than 50 in a primitive int[]: ");
	    Arrays.stream(greater_than_50).forEach(i -> System.out.print(i + " "));
	    
	    //Filter out all Integers greater than or equal to 40 (keep only those less than 40): 
	    int[] less_than_40 = new int[] {10,20,30,35,40,45,50,55,60};
	    less_than_40 = Arrays.stream(less_than_40).filter(i -> i < 40).toArray();
	    System.out.println("Filter out all Integers greater than 40 in primitive int[]: " + Arrays.toString(less_than_40));
	    
	    //Filter all Customers with an age less than or equal to 30 (keep those with age 30+):
	    List<Customer> custs_over_30 = custs.stream().filter(per -> per.getAge() > 30).collect(Collectors.toList());
	    System.out.println("Filter out all Customers with an age less than or equal to 30: ");
	    custs_over_30.forEach(per -> System.out.print(per.getAge() + " "));
	    
	    //Filter to only keep all Customers with an @gmail email address:
	    List<Customer> custs_with_gmail = custs.stream().filter(p -> p.getEmail() != null)
	    		                                        .filter(p -> p.getEmail().toLowerCase().contains("@gmail"))
	    												.collect(Collectors.toList());
	    System.out.println("\n Filter to keep ONLY the customers with an @gmail email address: ");
	    custs_with_gmail.forEach(c -> System.out.print(c.customerEmail != null ? c.customerEmail + " " : " NO EMAIL " ));
	    //Pitfalls: Look for nulls.  There could be a null inside any stream or lambda statement, so you need to use 
	    //          a ternary to deal with them.  Otherwise, you might get a null ref error during run time!
	    
	    //Filter out all Customers with a last name lexicographically < 'm' (tricky, need to split the name string!)
	    List<Customer> custs_less_than_m = custs.stream().filter(p -> (p.getName().toLowerCase().split(" ")[1] != null ? 
	    												  p.getName().toLowerCase().split(" ")[1] : p.getName().toLowerCase().split(" ")[0])
	    												 .compareTo("n") >= 0)
	    												 .collect(Collectors.toList());
	    System.out.println("\n Filters out all customers with a last name starting with < 'm': ");
	    custs_less_than_m.forEach(c -> System.out.print((
	    							   c.getName().split(" ")[1] != null ? 
	    							   c.getName().split(" ")[1] : c.getName().split(" ")[0]) + " "));
	    //Pitfalls: Since we can't be guaranteed that the string can be split on a space " ", we need to write a ternary
	    //          and check for null under the split at position 1 (which would be the person's Last name).  If it's not 
	    //          there then take the first name.  Also, check out the print statement.
	    
	    //Filter to find all Customers with an invalid phone number not containing exactly 10 digits: 
	    ArrayList<Customer> noinvalidphonenumbers = custs.stream().filter(per -> per.customerPhone.length() == 10)
	    		                                  .collect(Collectors.toCollection(ArrayList::new));
	    System.out.println("\n All customers with a valid phone number: ");
	    noinvalidphonenumbers.forEach(per -> System.out.print(per.getPhone() + " "));
	    //Pitfalls: Filter statement takes in what you want to KEEP (a bit counter-intuitive, but that's how it is)
	    //          Make sure to pass in a Predicate which describes what you would like to keep.
	    
	    //Filter to find all customers with age less than 40 and having a @gmail.com email address:
	    ArrayList<Customer> less40_and_gmail = custs.stream().filter(per -> per.getEmail() != null)
	    													 .filter(per -> per.getAge() < 40 && 
	    		                                                   per.getEmail().toLowerCase().contains("@gmail"))
	    		                                             .collect(Collectors.toCollection(ArrayList::new));
	    System.out.println("\n Find all customers with an age less than 40 and @gmail: ");
	    less40_and_gmail.forEach(c -> System.out.print(c.getAge() + " " + c.getEmail() + " "));
	    //Pitfalls: You CAN chain filters here, or you can just use a Predicate which describes both. 
	    //          Either way will work but it is best to chain less filters or stream statements when possible.
	    //          Also, make sure to check for nulls first before doing the 2'nd filter.
	    
	    //Use a forEach with a lambda to print every element of an ArrayList<Object>:
	    ArrayList<Object> tons_of_obj = new ArrayList<Object>(List.of(5,4,3,6,5,7,8,"exampleabnormality",6,4,10,3,2,3,4,5,6,5,2));
	    System.out.println("\n Print tons of objects: ");
	    tons_of_obj.forEach(someint -> System.out.print(someint != null ? someint + " " : " no number ")); 
	    //Pitfalls: You need to make sure that there is actually a non-null element at each location in the array before
	    //          printing.  Otherwise, you may get a null ref error when running.
	    
	    //Use a forEach with a lambda to print every element of an ArrayList<String>: after removing all 
	    //less than length 3, and then sorting lexicographically while ignoring case.
	    ArrayList<String> al_many_strings = new ArrayList<String>(List.of("abc","def","gsd","ZZZ","sdf","fhd","wer","re","mo","ve"));
	    al_many_strings = al_many_strings.stream().filter(str -> str.length() > 2).sorted()
	                                     .collect(Collectors.toCollection(ArrayList::new));
	    System.out.println("\n All strings with length greater than 2 and sorted alphabetically: ");
	    al_many_strings.forEach(str -> System.out.print(str + " "));
	    
	    //Use a forEach with a lambda to multiply every element of an ArrayList<Integer> by 2:
	    ArrayList<Integer> many_to_multiply = new ArrayList<Integer>(List.of(1,2,3,4,5));
	    many_to_multiply = many_to_multiply.stream().map(x -> x*2).collect(Collectors.toCollection(ArrayList::new));
	    System.out.println("\n Many from nn ArrayList multiplied by 2 ");
	    many_to_multiply.forEach(z -> System.out.print(z + " "));
	    //Pitfalls: What is the difference between an IntStream and a Stream<Integer> ?  
	    
	    //Use a forEach with a lambda to print every Customers name and age in Custs:
	    System.out.println("\n Every customer's name and age: ");
	    custs.forEach(c -> System.out.print(c.getName() + " " + c.getAge() + " "));
	    //Pitfalls: Again, make sure to use a ternary statement that does a null check.  
	    //          Otherwise you are asking for a null ref error!  Give a default for the null case. 
	    
		/* ------------------ END OF ARRAYS SECTION OF LAMBDAS STREAMS AND TERNARYS ------------------
		Key Takeaways:
			- Primitive arrays are streamed over using Arrays.stream(prim_arr_name) 
			- ArrayList<> and List<> are streamed over using AL_name.stream()
			 
			- After performing some operations on a stream and returning it, it returns either an IntStream,
			- Stream<Integer, Stream<String>, or Stream<Object>, etc.  It must be turned back into an array or ArrayList.
			
			- Stream is turned BACK into an ArrayList by using .collect(Collectors.toCollection(ArrayList::new))
			- Stream is turned BACK into a List by using collect(Collectors.toList())
			- Stream is turned BACK into a primitive array by using .toArray() method on the resultant stream.
					If the type of the primitive array needs to be anything other than int[], use toArray(String[]::new)
					or whatever array type the Object[] needs to be converted into.  toArray() returns an Object[] by default.
					
			- To compare every element of a stream against each other, use the (x,y) -> {...} lambda syntax.
		      Memorize this syntax and try not to overthink it.  This compares every x in the stream to every y.
		    
		    - To sort in regular order, using sorted() with no Comparator parameter.
		    - To sort in reverse order, use sorted(Comparator.reverseOrder())
		    - To sort in a custom way, use sorted((x,y) -> ...) and have the Lambda be the Comparator.
		          OR, you can create a custom Comparator functional.
		          You may want to complete a separate tutorial on just Comparators, as they are important
		          and come up in more than just Lambdas and Streams.
		          
		    - Make sure to create some sort of null catch for any values which can possibly contain a null.
		    - This can be a filter() statement, or a ternary which checks for null before returning the value.       
		      
			- To create a Comparator, either pass in Comparator.comparing(ClassName::MethodToCompareOn).
			     OR you can create your own Comparator using a Lambda + Ternary: (x,y) -> x > y ? 1 : -1		 
		---------------------------------------------------------------------------------------------- */
		
		/* --------------------------------- HASHTABLE AND HASHMAP:-----------------------------------
		How to use streams around Maps. (Note: Do not confuse a HashMap with the .Map() stream method!) */
		
		//Find the largest VALUE in a HashMap<String,Integer> (JUST the largest value)
		HashMap<String,Integer> hm1 = new HashMap<String,Integer>();
		Entry<String,Integer> default_entry = new AbstractMap.SimpleEntry<String,Integer>("defaultentry",100);
		hm1.put("Z",0); hm1.put("Y", 1); hm1.put("X", 2); hm1.put("W", 3); hm1.put("V", 4);
		int largest = hm1.values().stream().max(Integer::compare).orElse(-1);
	    System.out.println("Key associated with the maximum value in a Hashtable: " + largest);
		//Pitfalls: Make sure to use an orElse whenever using max() or min()  
		
		//Find the largest KEY in a HashMap<String,Integer> (JUST the largest key)
	    String largest_key = hm1.keySet().stream().max(String::compareTo).orElse("Default");
	    System.out.println("Maximum String key in Hashtable: " + largest_key);
		
		//Find the ENTRY in a HashMap<String,Integer> based on highest VALUE:
		Entry<String,Integer> highest_val_entry = hm1.entrySet().stream().max((v,k) -> v.getValue() > k.getValue() ?
				                                                              1 : -1).get(); 
		System.out.println("Find the largest ENTRY in a HashMap<String,Integer> based on VALUE: " +
				highest_val_entry.getKey() + " " + highest_val_entry.getValue()); 
		
		//Find the largest ENTRY in a HashMap<String,Integer> based on highest alphanumeric KEY:
		Entry<String,Integer> highest_key_entry = hm1.entrySet().stream().max((v,k) -> (v.getKey()).compareTo(k.getKey()) > 0 ? 
				                                                          1 : -1).get();
		System.out.println("Find the largest ENTRY in a HashMap<String,Integer> based on KEY: " 
				            + highest_key_entry.getKey() + " " + highest_key_entry.getValue());
		
		//Find the KEY in a HashMap<String,Integer> associated with the lowest VALUE:
		String key_of_lowest_val = hm1.entrySet().stream().min((v,k) -> v.getValue() - k.getValue()).get().getKey();
		System.out.println("Find the KEY in a HashMap<String,Integer> associated with the lowest VALUE: " + key_of_lowest_val);
		
		//Find the VALUE in a HashMap<String,Integer> associated with the lowest KEY:
		Integer val_of_lowest_key = hm1.entrySet().stream().min((v,k) -> (v.getKey()).compareTo(k.getKey()) < 0 ? 1 : -1)
				                                            .get().getValue();
		System.out.println("Find the VALUE in a HashMap<String,Integer> associated with the highest KEY: " + val_of_lowest_key);
		
		//Find the KEY in a HashMap<String,Integer> associated with the highest VALUE:		
		String key_of_highest_val = hm1.entrySet().stream().max((v,k) -> v.getValue() - k.getValue())
				                       .get().getKey();
		System.out.println("Find the KEY in a HashMap<String,Integer> associated with the highest VALUE: " + key_of_highest_val);
		
		//Find all Customers in the table CustomersTable where their index (integer) is less than 10:
	    HashMap<Integer, Customer> cust_table = createCustomersTable();
		HashMap<Integer,Customer> custs_with_low_index = cust_table.entrySet().stream().filter(c -> c.getKey() < 10)
				               .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (v,k) -> k, HashMap::new));
		System.out.println("\nFind all Customers in CustomersTable where index < 10: ");
		custs_with_low_index.entrySet().forEach(entry -> System.out.print(entry.getKey() + " " + entry.getValue().getName() + " "));
		
		//Find all indexes (integer) where Customer last name began with an 's':
		HashMap<Integer,Customer> lastname_s = cust_table.entrySet().stream().filter(
				                                          cust -> cust.getValue().getName().split(" ")[1] != null ?
				                                        		  cust.getValue().getName().split(" ")[1].startsWith("s") : 
				                                        	      cust.getValue().getName().split(" ")[0].startsWith("s"))
				                                          .collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue,
				                                        		                   (v,k) -> v, HashMap::new));
		System.out.println("\nFind all indexes where Customer last name began with an 's': ");
		lastname_s.entrySet().forEach(c -> System.out.print(c.getKey() + " " + c.getValue().getName()));
		
		/* ----------- END OF HASHTABLE AND HASHMAP SECTION OF LAMBDAS STREAMS AND TERNARYS -------------
		Key Takeaways: 
			- 
			- 
			- 
			- 
		-----------------------------------------------------------------------------------------------*/
	}
	
    static List<Customer> createCustomers() {
		List<Customer> custs = new ArrayList<Customer>();
		custs = List.of(
					new Customer("Simon Says","614-123-4567",40,"SimonSays123@gmail.com"),
					new Customer("Mark Smith","614-456-2342",45,"mSmith565@gmail.com"),
					new Customer("Howard Stern","844-123-4567",67,"howard.stern@gmail.com"),
					new Customer("Shelley Maldonado","202-555-0117",16,"shellymaldonado@gmail.com"),
					new Customer("Gaia Sheppard","654-321-4567",18,"gaiasheppard@aol.com"),
					new Customer("Kasper Conner","11111",27,"kasperconner@hotmail.com"),
					new Customer("Alberto Fuentes","22222",35,"albertofuentes@verizon.com"),
					new Customer("Cormac Tomiinson","3216549874",20,"cormactomiison@bing.com"),
					new Customer("Milo Ratcliffe","1234568521",22,"milo.ratcliffe123@gmail.com"),
					new Customer("Lewis Holmes","123456789456123",42,"lewis.holmes456@outlook.com"),
					new Customer("Howard Brett","1234567894",44,"howard.brett383@protonmail.com"),
					new Customer("Jarred Macias","321-654-7894",38,"jarred.macias834@outlook.com"),
					new Customer("Ursula Blackmore","1234568774",29,"ursula.blackmore843@yahoo.com"),
					new Customer("Julian Lowry","654-123-9874",25,"julian.lowry842@yahoo.com"),
					new Customer("Amelia-Grace Devila","121-456-7894",40,"amelia.grace.devila345@gmail.com"),
					new Customer("Rosanna Jackson","321-654-1345",35,"rosannajackson.forever@protonmail.com"),
					new Customer("Hailey Morty","615-485-4956",23,"hailey.morty.osu@aol.com"),
					new Customer("Falma Dunn","614-425-8582",18,"falma.dunn583@yahoo.com"),
					new Customer("Ayah Underwood","Frankfort, OH",19,"ayah.underwood@aol.com"),
					new Customer("Lilliana Webber","SomethingUnvalidated",52,"lilliana.iscool@gmail.com"),
					new Customer("Example null dude","WeirdoCase",35,null)
				);
		return custs;
	}
    
    static HashMap<Integer,Customer> createCustomersTable() {
    	HashMap<Integer, Customer> customers = new HashMap<Integer,Customer>();
    	customers.put(0, new Customer("Just seeing","987-654-3210",29,"IfThisWorks@gmail.com"));
    	customers.put(1, new Customer("Ecample dude","555-555-5555",24,"EcsKeyIsBroken@aol.com"));
    	customers.put(2, new Customer("James Someone","607-532-0952",35,"James.Someone@yahoo.com"));
    	customers.put(3, new Customer("Frankold Chesire","645-843-1648",45,"Frankold.Chesire@gmail.com"));
    	customers.put(4, new Customer("Lisa Porridge","654-156-0816",19,"Lisa.Porridge@protonmail.com"));
    	customers.put(5, new Customer("Michael Salton","794-615-4982",22,"Michael.Savant@gmail.com"));
    	customers.put(6, new Customer("Whose This","615-468-1865",65,"Whose.This@bing.com"));
    	customers.put(7, new Customer("OneHundred P. Effort","740-456-1234",53,"OneHundredPEffort@aol.com"));
    	customers.put(8, new Customer("Someone Else","530-948-8461",15,"SomeoneElse@hotmail.com"));
    	customers.put(9, new Customer("Michelle Obama","123-456-9874",70,"Michaelle.Obama567@gmail.com"));
    	customers.put(10, new Customer("Barack Obama","614-489-1684",42,"Barack.Hussein.Obama@hotmail.com"));
    	customers.put(11, new Customer("Donald Trump","614-874-9843",34,"DonaldPTrump@yahoo.com"));
    	customers.put(13, new Customer("Joseph Biden","051-465-4891",25,"JoeBiden@outlook.com"));
    	customers.put(14, new Customer("Hillary Clinton","614-764-8531",80,"HillaryClinton@gmail.com"));
    	customers.put(15, new Customer("George W Bush","316-145-6495",40,"GeorgeWBush@outlook.com"));
    	customers.put(16, new Customer("Saditya Patel","974-851-4652",61,"SadityaPatel@aol.com"));
    	customers.put(17, new Customer("Meadow Tsunami","614-486-4826",23,"MeadowTsunamiSoftworksLLC@outlook.com"));
    	customers.put(18, new Customer("Nancy Holson","456-123-7894",29,"NanyHolson@yahoo.com"));
    	customers.put(19, new Customer("Veronica Williams","321-654-9154",32,"VeronicaWilliams@protonmail.com"));
    	customers.put(20, new Customer("Frank Coolguy","984-164-4653",31,"FrankCoolguy@gmail.com"));
    	return customers;
    }

	static class Customer {
		private final String customerName;
		private final String customerPhone;
		private final String customerEmail;
		private final int customerAge;
		
		public Customer(String name, String phone, int age, String email) {
			this.customerAge = age;
			this.customerPhone = phone;
			this.customerName = name;
			this.customerEmail = email;
		}
		
		public String getName() {
			return this.customerName;
		}

		public int getAge() {
			return this.customerAge;
		}
		
		public String getEmail() {
			return this.customerEmail;
		}
		
		public String getPhone() {
			return this.customerPhone;
		}
	}
}
