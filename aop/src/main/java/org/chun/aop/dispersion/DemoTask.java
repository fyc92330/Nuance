package org.chun.aop.dispersion;

import org.springframework.stereotype.Component;

@DispersionLock
@Component
public class DemoTask implements TaskProcessor {

	@Override
	public void execute() {

		System.out.println(1);
	}

}
