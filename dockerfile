FROM ubuntu
RUN apt-get update
RUN apt-get install -y software-properties-common
RUN apt-get install -y python3-software-properties
RUN add-apt-repository ppa:mfikes/planck
RUN apt-get install -y planck
RUN apt-get install -y openjdk-8-jre-headless
RUN apt-get install -y curl
RUN curl -O https://download.clojure.org/install/linux-install-1.9.0.381.sh
RUN chmod +x linux-install-1.9.0.381.sh
RUN ./linux-install-1.9.0.381.sh