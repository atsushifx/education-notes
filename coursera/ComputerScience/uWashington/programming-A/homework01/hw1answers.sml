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






(* exec test *)
number_in_month ([(2012,2,28),(2013,12,1)],2) ;


