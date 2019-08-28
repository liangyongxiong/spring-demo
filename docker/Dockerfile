FROM openjdk:latest

LABEL name="WMS Adapter Image" vendor="hairoutech" license="GPLv2"

# env & alias
RUN cd /root \
    && echo "export TERM='xterm-256color'" >> /root/.bashrc \
    && echo "export LANG='en_US.UTF-8'" >> /root/.bashrc \
    && echo "export PS1='\[\033[01;31m\]\u@\h\[\033[00m\]:\[\033[01;34m\]\W\[\033[00m\]\$ '" >> /root/.bashrc \
    && echo "alias ll='ls -l'" >> /root/.bashrc \
    && echo "alias la='ls -a'" >> /root/.bashrc \
    && echo "alias lh='ls -h'" >> /root/.bashrc \
    && echo "alias rm='rm -i'" >> /root/.bashrc \
    && echo "alias mv='mv -i'" >> /root/.bashrc \
    && echo "alias cp='cp -i'" >> /root/.bashrc

COPY application.jar /root/workspace

ENV TZ=Asia/Shanghai
WORKDIR /workspace

CMD ["java", "-jar", "application.jar"]

