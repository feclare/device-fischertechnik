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

public class TA_INPUT {
	public List<Integer> uni = Arrays.asList(new Integer[8]);
	public List<Integer> cnt_in = Arrays.asList(new Integer[4]);
	public List<Integer> counter = Arrays.asList(new Integer[4]);
	public Integer display_button_left = 0;
	public Integer display_button_right = 0;
	public List<Integer> reserved = Arrays.asList(new Integer[20]);
	
	public TA_INPUT(String ta_input) {
		for(int i = 0; i < uni.size(); i++)
			uni.set(i, Integer.parseInt(ta_input.substring(i*4,i*4+4), 16));
		ta_input = ta_input.substring(uni.size()*4);
		for(int i = 0; i < cnt_in.size(); i++)
			cnt_in.set(i, Integer.parseInt(ta_input.substring(i*2,i*2+2), 16));
		ta_input = ta_input.substring(cnt_in.size()*4);
		for(int i = 0; i < counter.size(); i++)
			counter.set(i, Integer.parseInt(ta_input.substring(i*4,i*4+4), 16));
		ta_input = ta_input.substring(counter.size()*4);
		display_button_left = Integer.parseInt(ta_input.substring(0,4), 16);
		display_button_right = Integer.parseInt(ta_input.substring(4,8), 16);
		ta_input = ta_input.substring(2*4);
		for(int i = 0; i < reserved.size(); i++)
			reserved.set(i, Integer.parseInt(ta_input.substring(i,i+2), 16));
	}

	
	public TA_INPUT() {
		for(int i = 0; i < uni.size(); i++)
			uni.set(i, 0);
		for(int i = 0; i < cnt_in.size(); i++)
			cnt_in.set(i, 0);
		for(int i = 0; i < counter.size(); i++)
			counter.set(i, 0);
		for(int i = 0; i < reserved.size(); i++)
			reserved.set(i, 0);
	}

	@Override
	public String toString() {
		String out = "uni: "; // I5: left, I6: right, I7: top, I8: bottom
		for(int i = 0; i < uni.size(); i++)
			out += String.format("%04X ", uni.get(i));
		return out;
	}

	public int getUni(int uniNum) {
		int uni = uniNum - 1;
		int value = this.uni.get(uni);
		return (value == 0) ? 0 : 1;
	}
}
