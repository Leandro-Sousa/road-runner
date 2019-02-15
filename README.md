# road-runner

Only executes this command and Enjoy =)

git clone https://github.com/Leandro-Sousa/road-runner.git 
&& cd road-runner 
&& gradle build 
&& docker build -t leandrosousa/roadrunner . 
&& docker run -itd -p 80:80 leandrosousa/roadrunner

After that... testing

curl http://localhost/api/users/Leandro-Sousa
