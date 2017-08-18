/*******************************************************************************
 * Copyright 2016-2017 Dell Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * @microservice:  device-fischertechnik
 * @author: Tyler Cox, Dell
 * @version: 1.0.0
 *******************************************************************************/
package org.edgexfoundry.fishX1;

import java.util.Arrays;
import java.util.List;

public class TA_OUTPUT {
	public List<Integer> cnt_reset = Arrays.asList(new Integer[4]);
	public List<Integer> master = Arrays.asList(new Integer[4]);
	public List<Integer> duty = Arrays.asList(new Integer[8]);
	public List<Integer> distance = Arrays.asList(new Integer[4]);
	public List<Integer> motor_ex_cmd_id = Arrays.asList(new Integer[4]);
	
	public TA_OUTPUT() {
		for(int i = 0; i < cnt_reset.size(); i++)
			cnt_reset.set(i, 0);
		for(int i = 0; i < master.size(); i++)
			master.set(i, 0);
		for(int i = 0; i < duty.size(); i++)
			duty.set(i, 0);
		for(int i = 0; i < distance.size(); i++)
			distance.set(i, 0);
		for(int i = 0; i < motor_ex_cmd_id.size(); i++)
			motor_ex_cmd_id.set(i, 0);
	}
	
	public void setDuty(int motorNum, int power) {
		int motor = motorNum - 1;
		// 0: press down, 1: press up, 2: belt right, 3: belt left, 5: light
		if (power > 0) {
			duty.set(motor * 2, power);
			duty.set(motor * 2 + 1, 0);
		} else {
			duty.set(motor * 2, 0);
			duty.set(motor * 2 + 1, -1 * power);
		}
	}
	
	public int getDuty(int motorNum) {
		int motor = motorNum - 1;
		int power = duty.get(motor * 2);
		if (power == 0)
			power = -1 * duty.get(motor * 2 + 1);
		return power;
	}
	
	@Override
	public String toString() {
		String out = "";
		for(int i = 0; i < cnt_reset.size(); i++)
			out += String.format("%04X", cnt_reset.get(i));
		for(int i = 0; i < master.size(); i++)
			out += String.format("%02X", master.get(i));
		for(int i = 0; i < duty.size(); i++)
			out += String.format("%04X", duty.get(i));
		for(int i = 0; i < distance.size(); i++)
			out += String.format("%04X", distance.get(i));
		for(int i = 0; i < motor_ex_cmd_id.size(); i++)
			out += String.format("%04X", motor_ex_cmd_id.get(i));
		return out;
	}
}
