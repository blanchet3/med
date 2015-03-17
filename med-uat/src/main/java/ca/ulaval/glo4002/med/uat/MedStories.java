package ca.ulaval.glo4002.med.uat;

import static java.util.Arrays.*;
import static org.jbehave.core.io.CodeLocations.*;
import static org.jbehave.core.reporters.Format.*;

import java.util.Arrays;
import java.util.List;

import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.embedder.Embedder;
import org.jbehave.core.failures.FailingUponPendingStep;
import org.jbehave.core.failures.PendingStepStrategy;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.ScanningStepsFactory;

public class MedStories extends JUnitStories {

    private PendingStepStrategy pendingStepStrategy = new FailingUponPendingStep();

    private Format[] formats = new Format[]{CONSOLE};
    private StoryReporterBuilder reporterBuilder = new StoryReporterBuilder()
            .withCodeLocation(codeLocationFromClass(MedStories.class)).withFailureTrace(true).withFailureTraceCompression(true)
            .withDefaultFormats().withFormats(formats);

    public static void main(String[] args) {
        new MedStories().embedder.runAsEmbeddables(Arrays.asList(MedStories.class.getCanonicalName()));
    }

    private Embedder embedder = new Embedder();

    public MedStories() {
        useEmbedder(embedder);
    }

    @Override
    public Configuration configuration() {
        return new MostUsefulConfiguration().usePendingStepStrategy(pendingStepStrategy)
                .useStoryLoader(new LoadFromClasspath(getClass().getClassLoader())).useStoryReporterBuilder(reporterBuilder);
    }

    @Override
    public InjectableStepsFactory stepsFactory() {
        // Le ScanningStepsFactory implique qu'on doit ajouter une dépendence vers org.reflections. Voir le POM.
        return new ScanningStepsFactory(configuration(), getClass());
    }

    @Override
    protected List<String> storyPaths() {
        return new StoryFinder().findPaths(codeLocationFromClass(this.getClass()).getFile(), asList("**/*.story", "*.story"), null);
    }

}

// *** Changing the language is not easy, here is how to make it detect the french keywords :
//
// PendingStepStrategy pendingStepStrategy = new FailingUponPendingStep();
// Format[] formats = new Format[] { CONSOLE };
// StoryReporterBuilder reporterBuilder = new StoryReporterBuilder()
// .withKeywords(new LocalizedKeywords(Locale.FRENCH))
// .withCodeLocation(codeLocationFromClass(MedStoriesRunner.class)).withFailureTrace(true)
// .withFailureTraceCompression(true).withDefaultFormats().withFormats(formats)
// .withCrossReference(new CrossReference());
//
// @Override
// public Configuration configuration() {
// LocalizedKeywords keywords = new LocalizedKeywords(new Locale("fr"));
// return new MostUsefulConfiguration()
// .useKeywords(keywords)
// .usePendingStepStrategy(pendingStepStrategy)
// .useStoryParser(new RegexStoryParser(keywords))
// .useStoryLoader(new LoadFromClasspath(getClass().getClassLoader()))
// .useStoryReporterBuilder(reporterBuilder);
// }
