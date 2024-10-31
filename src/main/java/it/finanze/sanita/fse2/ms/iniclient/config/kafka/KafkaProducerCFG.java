// package it.finanze.sanita.fse2.ms.iniclient.config.kafka;

// import java.net.InetAddress;
// import java.net.UnknownHostException;
// import java.util.HashMap;
// import java.util.Map;

// import org.apache.commons.lang3.StringUtils;
// import org.apache.kafka.clients.producer.ProducerConfig;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.beans.factory.annotation.Qualifier;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.core.env.Environment;
// import org.springframework.kafka.core.DefaultKafkaProducerFactory;
// import org.springframework.kafka.core.KafkaTemplate;
// import org.springframework.kafka.core.ProducerFactory;

// import lombok.extern.slf4j.Slf4j;

// /**
// * Kafka producer configuration.
// */
// @Configuration
// @Slf4j
// public class KafkaProducerCFG {

// /**
// * Kafka properties.
// */
// @Autowired
// private KafkaPropertiesCFG kafkaPropCFG;

// /**
// * Kafka producer properties.
// */
// @Autowired
// private KafkaProducerPropertiesCFG kafkaProducerPropCFG;

// /**
// * Non transactional producer config.
// */
// @Bean
// public Map<String, Object> producerWithoutTransactionConfigs() {
// Map<String, Object> props = new HashMap<>();

// props.put(ProducerConfig.CLIENT_ID_CONFIG,
// kafkaProducerPropCFG.getClientId()+ "-noTx");
// props.put(ProducerConfig.RETRIES_CONFIG, kafkaProducerPropCFG.getRetries());
// props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
// kafkaPropCFG.getProducerBootstrapServers());
// props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
// kafkaProducerPropCFG.getKeySerializer());
// props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
// kafkaProducerPropCFG.getValueSerializer());

// if (!StringUtils.isBlank(kafkaPropCFG.getProtocol())) {
// props.put("security.protocol", kafkaPropCFG.getProtocol());
// }
// if (!StringUtils.isBlank(kafkaPropCFG.getMechanism())) {
// props.put("sasl.mechanism", kafkaPropCFG.getMechanism());
// }
// if (!StringUtils.isBlank(kafkaPropCFG.getConfigJaas())) {
// props.put("sasl.jaas.config", kafkaPropCFG.getConfigJaas());
// }
// if (!StringUtils.isBlank(kafkaPropCFG.getTrustoreLocation())) {
// props.put("ssl.truststore.location", kafkaPropCFG.getTrustoreLocation());
// }
// if (kafkaPropCFG.getTrustorePassword() != null &&
// kafkaPropCFG.getTrustorePassword().length > 0) {
// props.put("ssl.truststore.password",
// String.valueOf(kafkaPropCFG.getTrustorePassword()));
// }

// return props;
// }

// /**
// * Non transactional producer.
// */
// @Bean
// @Qualifier("notxkafkatemplateFactory")
// public ProducerFactory<String, String> producerFactoryWithoutTransaction() {
// log.info("Initialization of non transactional Factory");
// return new
// DefaultKafkaProducerFactory<>(producerWithoutTransactionConfigs());
// }

// /**
// * Non transactional kafka template.
// */
// @Bean
// @Qualifier("notxkafkatemplate")
// public KafkaTemplate<String, String> notxKafkaTemplate() {
// return new KafkaTemplate<>(producerFactoryWithoutTransaction());
// }

// }