package KafkaProducer;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;

public class KafkaProducerSendMessage 
{
	public void SendingMessageKafkaProtocol()
	{

        String eventHubNamespace = "y-event-hub-namespace";
        String eventHubName = "eventHubName-myeventhub";
        String saslUsername = "$ConnectionString"; // Keep this literal for Kafka in Event Hubs
        String saslPassword = "value of Connection string";

        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, eventHubNamespace);
        properties.put("security.protocol", "SASL_SSL");
        properties.put("sasl.mechanism", "PLAIN");
        properties.put("sasl.jaas.config",
                "org.apache.kafka.common.security.plain.PlainLoginModule required username=\"" + saslUsername + "\" password=\"" + saslPassword + "\";");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);

        try 
        {
            String message = "Hello from Java using Kafka protocol!";
            ProducerRecord<String, String> record = new ProducerRecord<>(eventHubName, "key", message);
            
            RecordMetadata metadata = producer.send(record).get(); // Synchronously get result
            System.out.println("Message sent to partition " + metadata.partition() + " with offset " + metadata.offset());
        } 
        catch (InterruptedException | ExecutionException e) 
        {
            System.err.println("Failed to send message: " + e.getMessage());
        } 
        finally
        {
            producer.close(); // Close producer to release resources
        }

	}
}
