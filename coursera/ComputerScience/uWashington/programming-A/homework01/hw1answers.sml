(* ProgrammingLanguage-A Assignment 1 *)

(* create date function by hw1.pdf *)
(* date [year:int * month:int * day:int] .. tuple *)

fun is_older(date1:int*int*int, date2:int*int*int) =
	if #1 date1 < #1 date2 then
		true
	else if (#1 date1 = #1 date2) then
		if #2 date1 < #2 date2 then
			true
		else if (#2 date1 = #2 date2) then
			#3 date1 < #3 date2
		else
			false
	else
		false
	;


(* 2. : how many dates in the list are in the given month *)
fun number_in_month(dates:(int*int*int) list, month:int) =
	if (null dates) then
		0
	else
		let
		  val h_month = #2 (hd dates)
		in
			if h_month = month then
		  		1 + number_in_month(tl dates, month)
			else
				number_in_month(tl dates, month)
		end
	;


(* 3. : add month number from list *)
fun number_in_months(dates:(int*int*int) list, months:int list) =
    if (null months) then
        0
    else
        number_in_month(dates, hd months) + number_in_months(dates, tl months)
	;


(* 4. : create date list from parameter date list where month = parameter *)
fun dates_in_month(dates:(int*int*int) list, month: int) =
	if (null dates) then
        []
    else
		let
			val h_month = #2 (hd dates)
		in
			if h_month = month then
				(hd dates)::dates_in_month(tl dates, month)
			else
				dates_in_month(tl dates, month)
		end
	;

(* 5. : create date list from date lisr & month list *)
fun dates_in_months(dates:(int*int*int) list, months: int list) =
	if (null months) then
        []
    else
		dates_in_month(dates, hd months) @ dates_in_months(dates, tl months)
	;


(* 6. get string fromn string list countd by n *)
fun get_nth(ss: string list, n: int) =
	if (null ss) then
		""
	else if n = 1 then
		hd ss
	else
		get_nth(tl ss, n - 1)
;


(* 7. date to string (English format) *)
fun date_to_string(date:(int*int*int)) =
    let
		fun date_to_month(date:(int*int*int)) =
    	let
    	    val m = #2 date
		in
			if m = 1 then
				"January"
			else if m = 2 then
    	        "February"
			else if m = 3 then
    	        "March"
			else if m = 4 then
    	        "April"
			else if m = 5 then
    	        "May"
			else if m = 6 then
    	        "June"
			else if m = 7 then
    	        "July"
			else if m = 8 then
    	        "August"
			else if m = 9 then
    	        "September"
			else if m = 10 then
    	        "October"
			else if m = 11 then
    	        "November"
			else if m = 12 then
    	        "December"
    	    else
				"???"
			end
	in
		date_to_month(date) ^ " " ^ Int.toString (#3 date) ^ ", " ^ Int.toString (#1 date)
	end
	;

(* 8 number_before_reaching_sum *)
fun number_before_reaching_sum(sum: int, numlist: int list) =
	let
		fun sumn (xs: int list, n: int) =
			if (null xs) then
				0
			else if n = 1 then
        		hd xs
    		else
				hd xs + sumn(tl xs, n - 1)
		;
		fun checkn(xs: int list, count: int) =
			if sumn(xs, count + 1) >= sum then
				count
			else
				checkn(xs, count + 1)
	in
		checkn(numlist, 0)
	end
	;

(* 9. what_month : number of days to month number *)
fun what_month(number_of_days: int) =
	number_before_reaching_sum(number_of_days, [0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31])
	;

(* 10 month_range *)
fun month_range(from: int, to: int) =
	if to < from then
		[]
	else
		what_month(from)::month_range(from+1, to)
    ;

(* 11. oldest :  *)

	;

fun oldest(daylist: (int*int*int) list) =
	if (null daylist) then
		NONE
	else
		let

			fun oldest_noempty(daylist: (int*int*int) list) =
				if (null (tl daylist)) then
					hd daylist
				else
					let
						val tl_oldest =  oldest_noempty(tl daylist)
					in
						if (is_older (hd daylist, tl_oldest)) then
							hd daylist
						else
                			tl_oldest
					end
		in
			SOME (oldest_noempty(daylist))
		end
	;

(* exec test *)

(*
[is_older ((1,2,3),(2,3,4)), true];
[number_in_month ([(2012,2,28),(2013,12,1)],2) , 1];
[number_in_months ([(2012,2,28),(2013,12,1),(2011,3,31),(2011,4,28)],[2,3,4]),  3];
dates_in_month ([(2012,2,28),(2013,12,1)],2);
dates_in_months ([(2012,2,28),(2013,12,1),(2011,3,31),(2011,4,28)],[2,3,4]) ;
[get_nth(["hi", "there", "how", "are", "you"], 2), "there"];
[date_to_string (2013, 6, 1), "June 1, 2013"];
[number_before_reaching_sum(10, [1,2,3,4,5]), 3];
[what_month 70, 3];
[month_range (31, 34), [1,2,2,2]];
[oldest [(2012,2,28),(2011,3,31),(2011,4,28)] , SOME (2011,3,31)];
*)
