package com.loopbit.distance;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Levenshtein {
	
	private String string;
	private int max_edits;
	
	public Levenshtein(String string, int max_edits) {
		this.string = string;
		this.max_edits = max_edits;
	}
	
	public boolean within(String query) {
		Integer[][] state = this.start();
		for(char c : query.toCharArray()) {
			state = step(state, c);
			if(!can_match(state)) {
				return false;
			}
		}
		return true;
	}
	
	private Integer[][] start() {
		return new Integer[][] {
			IntStream.range(0,this.max_edits+1).boxed().toArray(Integer[]::new),
			IntStream.range(0,this.max_edits+1).boxed().toArray(Integer[]::new),
		};
	}

	private Integer[][] step(Integer[][] state, char letter) {
		List<Integer> new_indices = new ArrayList<Integer>();
		List<Integer> new_values = new ArrayList<Integer>();
		
		Integer[] indices = state[0];
		Integer[] values = state[1];
		
		if(indices.length > 0 && indices[0] == 0 && values[0] < this.max_edits) {
			new_indices.add(0);
			new_values.add(values[0] + 1);
		}
		
		int j = 0;
		for(int i = indices[0]; i <= indices[indices.length - 1]; i++) {
			if(i == this.string.length()) break;
			int val = (this.string.charAt(i) == letter) ? 0 : 1;		
			if(values.length > j) {
				val += values[j];
			}
			if(new_indices.size() > 0 && new_indices.get(new_indices.size() - 1) == i) {
				val = Math.min(val, new_values.get(new_values.size() - 1) + 1);
			}
			if(indices.length > j+1 && indices[j+1] == i+1) {
				val = Math.min(val, values[j+1] + 1);
			}
			if(val <= this.max_edits) {
				new_indices.add(i+1);
				new_values.add(val);
			}
			j++;
		}

		Integer[] sample = {};
		return new Integer[][] {
			new_indices.toArray(sample),
			new_values.toArray(sample)
		};
		
	}

	private boolean can_match(Integer[][] state) {
		return state[0].length > 0;
	}
	/*
	private boolean is_match(Integer[][] state) {
		return (state[0].length > 0 && state[0][state[0].length -1] == this.string.length());
	}
	
	*/
}
