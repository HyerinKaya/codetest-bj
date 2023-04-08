package codetest;

import java.util.*;
import java.io.*;

public class codetree_playtag {

	public static int n, m, h, k, tree[][], itMove, bigCnt, rCnt, cCnt, point, turn;
	public static Queue<Spot> runnerQ;
	public static Spot it;
	public static int[] dr = {-1, 0, 1, 0}; //^ > v <
	public static int[] dc = {0, 1, 0, -1};
	public static Stack<Spot> stack;

	public static void main(String args[]) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		runnerQ = new LinkedList<>();
		it = new Spot(n/2, n/2, 0);
		tree = new int[n][n];
		bigCnt = 1;
		rCnt = 0;
		cCnt = 0;
		itMove = 1; //it rotate direction 1:outward 2:inward
		point = 0;
		turn=0;
		stack = new Stack<>();

		for(int i=0;i<m;i++) {
			StringTokenizer str = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(str.nextToken())-1;
			int c = Integer.parseInt(str.nextToken())-1;
			int d = Integer.parseInt(str.nextToken());
			Spot spot = new Spot(r, c, d);
			runnerQ.add(spot);

		}

		for(int i=0;i<h;i++) {
			StringTokenizer str = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(str.nextToken())-1;
			int c = Integer.parseInt(str.nextToken())-1;
			tree[r][c] = 1;
		}

		while(turn<k) {
//			System.out.println("turn: "+turn);
//			runnerQ.forEach(spot -> System.out.print("("+spot.r+", "+spot.c+"/dir:"+spot.d+")"));
//			System.out.println();

			runnerMove();

			itMove();

			catchRunner();

			turn+=1;
		}

		System.out.println(point);

	}

	static class Spot{
		int r, c, d;
		Spot(int r, int c, int d){
			this.r = r;
			this.c = c;
			this.d = d;
		}
	}

	static void runnerMove() {
		int qsize = runnerQ.size();
		for(int i=0;i<qsize;i++) {
			Spot spot = runnerQ.remove();
			if(inLen(spot)) {
				int rr = spot.r+dr[spot.d];
				int cc = spot.c+dc[spot.d];
				if(inRange(rr, cc)&&!isIt(rr, cc)) { // in range, no it > move
					spot.r = rr;
					spot.c = cc;
				}else if(!inRange(rr, cc)){ //out of range
					spot.d = spot.d ^ 2; //turn opposite direction
					rr = spot.r+dr[spot.d];
					cc = spot.c+dc[spot.d];
					if(!isIt(rr,cc)) { //no it > move
						spot.r = rr;
						spot.c = cc;
					}
				}
			}
			runnerQ.offer(spot);
		}

	}

	static void itMove() {

		if(itMove==1) { //outward
			if(rCnt<bigCnt) {
				rCnt+=1;
				it.r += Math.pow(-1, bigCnt);
				if(rCnt==bigCnt) {
					if(it.d>=3) {
						it.d =0;
					}else {
						it.d+=1;
					}
				}
			}else if(rCnt>=bigCnt&&cCnt<bigCnt) {
				cCnt+=1;
				it.c += Math.pow(-1, bigCnt+1);
				if(cCnt==bigCnt) {
					if(it.d>=3) {
						it.d =0;
					}else {
						it.d+=1;
					}
				}
			}else if(rCnt>=bigCnt&&cCnt>=bigCnt) {
				rCnt=0;
				cCnt=0;
				bigCnt+=1;
				itMove();
			}

		}else if(itMove==2) {  //inward
			if(cCnt>0) {
				cCnt-=1;
				it.c += Math.pow(-1, bigCnt);
				if(cCnt==0) {
					if(it.d<=0) {
						it.d=3;
					}else {
						it.d-=1;
					}
				}
			}else if(cCnt<=0&&rCnt>0) {
				rCnt-=1;
				it.r += Math.pow(-1, bigCnt+1);
				if(rCnt==0) {
					if(it.d<=0) {
						it.d=3;
					}else {
						it.d-=1;
					}
				}
			}else if(rCnt<=0&&cCnt<=0) {
				rCnt=bigCnt-1;
				cCnt=bigCnt-1;
				bigCnt-=1;
				itMove();
			}
		}

		if(it.r==0&&it.c==0) {//change direction inward
			it.d = 2;
			itMove = 2;
		}else if(it.r==n/2&&it.c==n/2) { //change direction outward
			it.d = 0;
			itMove = 1;
		}

	}

	static void catchRunner() {
		Spot[] arr = new Spot[3];
		int catchCnt =0;

		for(int i=0;i<3;i++) {
			int rr = it.r+dr[it.d]*i;
			int cc = it.c+dc[it.d]*i;
			Spot spot = new Spot(rr, cc, 0);
			arr[i] = spot;
		}

		int qsize = runnerQ.size();
		for(int i=0; i<qsize;i++) {
			Spot runner = runnerQ.remove();
			int r = runner.r;
			int c = runner.c;
			boolean caught = false;
			for(int j=0; j<3;j++) {
//				System.out.print("arr["+j+"]: ("+arr[j].r+", "+arr[j].c+") runner: ("+r+", "+c+") tree:"+tree[r][c]+" catch:"+catchCnt);
//				System.out.println();
				if(r==arr[j].r&&c==arr[j].c&&tree[r][c]!=1) { //runner in it view(3) and no tree
					catchCnt+=1;
					caught = true;
					break;
				}
			}
			if(!caught) runnerQ.offer(runner);
		}
		point += (turn+1)*catchCnt;
	}

	static boolean inLen(Spot spot){ //runner and it length 3
		return(Math.abs(it.r-spot.r)+Math.abs(it.c-spot.c)<=3);

	}

	static boolean inRange(int r, int c) {
		return(r>=0&&r<n&&c>=0&&c<n);
	}

	static boolean isIt(int r, int c) {
		return(it.r==r&&it.c==c);
	}


}
