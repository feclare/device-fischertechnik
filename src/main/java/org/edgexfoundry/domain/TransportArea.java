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
package org.edgexfoundry.domain;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class TransportArea {
	
	public TA_OUTPUT getTA_OUTPUT() {
		return new TA_OUTPUT();
	}
	
	public TA_INPUT getTA_INPUT(String input) {
		return new TA_INPUT(input);
	}
	
	public class TA_OUTPUT {
		public List<Integer> cnt_reset = Arrays.asList(new Integer[4]);
		public List<Integer> master = Arrays.asList(new Integer[4]);
		public List<Integer> duty = Arrays.asList(new Integer[8]);
		public List<Integer> distance = Arrays.asList(new Integer[4]);
		public List<Integer> reserved = Arrays.asList(new Integer[4]);
		
		public TA_OUTPUT() {
			for(int i = 0; i < cnt_reset.size(); i++)
				cnt_reset.set(i, 0);
			for(int i = 0; i < master.size(); i++)
				master.set(i, 0);
			for(int i = 0; i < duty.size(); i++)
				duty.set(i, 0);
			for(int i = 0; i < distance.size(); i++)
				distance.set(i, 0);
			for(int i = 0; i < reserved.size(); i++)
				reserved.set(i, 0);
		}
		
		public void setDuty(int motor, int cw, int ccw) {
			duty.set(motor * 2, cw);
			duty.set(motor * 2 + 1, ccw);
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
			for(int i = 0; i < reserved.size(); i++)
				out += String.format("%04X", reserved.get(i));
			return out;
		}
	}
	
	public class TA_INPUT {
		public List<Integer> uni = Arrays.asList(new Integer[8]);
		public List<Integer> cnt_in = Arrays.asList(new Integer[4]);
		public List<Integer> counter = Arrays.asList(new Integer[4]);
		public Integer display_button_left = 0;
		public Integer display_button_right = 0;
		public List<Integer> cnt_resetted = Arrays.asList(new Integer[4]);
		public List<Integer> motor_pos_reached = Arrays.asList(new Integer[4]);
		public List<Integer> reserved = Arrays.asList(new Integer[16]);
		
		public TA_INPUT(String ta_input) {
			for(int i = 0; i < uni.size(); i++)
				uni.set(i, Integer.parseInt(ta_input.substring(i*4,i*4+4), 16));
			ta_input = ta_input.substring(uni.size()*4);
			for(int i = 0; i < cnt_in.size(); i++)
				cnt_in.set(i, Integer.parseInt(ta_input.substring(i*4,i*4+4), 16));
			ta_input = ta_input.substring(cnt_in.size()*4);
			for(int i = 0; i < counter.size(); i++)
				counter.set(i, Integer.parseInt(ta_input.substring(i*4,i*4+4), 16));
			ta_input = ta_input.substring(counter.size()*4);
			display_button_left = Integer.parseInt(ta_input.substring(0,4), 16);
			display_button_right = Integer.parseInt(ta_input.substring(4,8), 16);
			ta_input = ta_input.substring(2*4);
			for(int i = 0; i < cnt_resetted.size(); i++)
				cnt_resetted.set(i, Integer.parseInt(ta_input.substring(i*4,i*4+4), 16));
			ta_input = ta_input.substring(cnt_resetted.size()*4);
			for(int i = 0; i < motor_pos_reached.size(); i++)
				motor_pos_reached.set(i, Integer.parseInt(ta_input.substring(i*4,i*4+4), 16));
			ta_input = ta_input.substring(motor_pos_reached.size()*4);
			for(int i = 0; i < reserved.size(); i++)
				reserved.set(i, Integer.parseInt(ta_input.substring(i,i+1), 16));
		}

		
		@Override
		public String toString() {
			String out = "uni: ";
			for(int i = 0; i < uni.size(); i++)
				out += String.format("%04d, ", uni.get(i));
			out += "\ncnt_in: ";
			for(int i = 0; i < cnt_in.size(); i++)
				out += String.format("%04d, ", cnt_in.get(i));
			out += "\ncounter: ";
			for(int i = 0; i < counter.size(); i++)
				out += String.format("%04d, ", counter.get(i));
			out += "\ndisplay_button_left: " + display_button_left;
			out += "\ndisplay_button_right: " + display_button_right;
			out += "\ncnt_resetted: ";
			for(int i = 0; i < cnt_resetted.size(); i++)
				out += String.format("%04d, ", cnt_resetted.get(i));
			out += "\nmotor_pos_reached: ";
			for(int i = 0; i < motor_pos_reached.size(); i++)
				out += String.format("%04d, ", motor_pos_reached.get(i));
			out += "\nreserved: ";
			for(int i = 0; i < reserved.size(); i++)
				out += String.format("%01d, ", uni.get(i));
			return out;
		}
	}
	
}
