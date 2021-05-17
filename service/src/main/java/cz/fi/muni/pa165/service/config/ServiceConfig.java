package cz.fi.muni.pa165.service.config;
import cz.fi.muni.pa165.MusicBandManagerApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
@Configuration
@Import(MusicBandManagerApplicationContext.class)
@ComponentScan(basePackages = {"cz.fi.muni.pa165.service.service", "cz.fi.muni.pa165.service.facade"})
public class ServiceConfig {
}