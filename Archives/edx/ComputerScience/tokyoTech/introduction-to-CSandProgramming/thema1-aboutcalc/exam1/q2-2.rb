puts('Number?')
a=gets().to_i
b=gets().to_i

if a>=0 && b>0
	amari = a
	while amari > b
		amari = amari - b
	end
elsif a>=0 && b<0
	puts('a>=0 & b<0')
	amari = a
	while amari > 0
		amari = amari + b
	end
elsif a<0 && b>0
	puts('a<0 & b>0')
	amari = a
	while amari < 0
		amari = amari + b
	end
elsif a<0 && b<0
	puts('a<0 & b<0');
	amari = a
	while amari <= b
		amari = amari - b
	end
else
	puts('Error: b = 0')
	exit 1
end
r = a % b
puts('amari = ' + r.to_s + " / " + amari.to_s )

