package com.orainteractive.simplechat.service;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses(value = { 
		UserServiceTest.class,
		ChatServiceTest.class,
		ChatMessageServiceTest.class
})
public class AllServiceTest {

}
