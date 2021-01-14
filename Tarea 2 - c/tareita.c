int reading(){
    Token tok;
    tok = gettoken();
    if (tok == 'END')
        return ACCEPT;
    else
	return shifting(tok);
}

int shifting(Token tok){
    if (shift(tok))
        return reading();
    else
	return reducing(tok);
}

int reducing(Token tok){
    if (reduce(tok))
	return shifting(tok);
    else
	return ERROR;

int parse(){
    return reading();
}