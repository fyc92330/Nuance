import org.junit.jupiter.api.Test;

import java.util.function.Function;

//@SpringBootTest(classes = SomnusApplication.class)
class SomnusApplicationTests {

	@Test
	void contextLoads() {

		final String[] exams = {"23", "24", "103", "362", "666"};
		for (String exam : exams) {
			demo(exam);
		}
	}

	private void demo(String exam){
		Function<String, Integer> getCount = str -> {
			int i = Integer.parseInt(str);
			return i % 3 == 0 ? 4 : 3;
		};

		int result = 0, total = 0, result2 = 0;
		for (char c : exam.toCharArray()) {
			total += Integer.parseInt(String.valueOf(c));
		}


		for (char c : exam.toCharArray()) {
			result += getCount.apply(String.valueOf(c));
			result2 += getCount.apply(String.valueOf(total - Integer.parseInt(String.valueOf(c))));
		}

		System.out.println("total = " + total);
		System.out.println("exam = "+exam+", ans = " + result);
		System.out.println("exam = "+exam+", ans2 = " + result2);
	}

}
