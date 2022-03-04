package test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Test {

	public static void main(String[] args) {
		int size = 3;
		
		List<Integer> list = IntStream.range(1, 30).skip(2 * size).limit(size)
				.limit(size)
				.boxed()
				.collect(Collectors.toList());
		
		System.out.println(list);;
	}

}
