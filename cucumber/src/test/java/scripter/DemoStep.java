package scripter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
public class DemoStep {

	private final List<Integer> demoList = new ArrayList<>();
	private Stream<Integer> integerStream;

	@Given("把參數 {int} {int} {int} {int} {int} 加入集合")
	public void 把參數加入集合(int arg0, int arg1, int arg2, int arg3, int arg4) {
		demoList.add(arg0);
		demoList.add(arg1);
		demoList.add(arg2);
		demoList.add(arg3);
		demoList.add(arg4);
		integerStream = demoList.stream();
	}

	@Then("在流的狀態下參數變為兩倍")
	public void 在流的狀態下參數變為兩倍() {
		integerStream = integerStream.map(a -> a*2);
	}

	@And("過濾掉不是 {int} 的倍數")
	public void 過濾掉不是的倍數(int arg0) {
		integerStream = integerStream.filter(a -> a%3==0);
	}

	@Then("印出剩下的值")
	public void 印出剩下的值() {
		integerStream.forEach(System.out::println);
	}
}
