package me.qoomon.maven.gitversioning;

import org.apache.maven.model.Model;
import org.apache.maven.model.Parent;

/**
 * Maven artifact identifier consisting of groupId / artifactId / getVersion.
 */
class GAV {
    private String groupId;
    private String artifactId;
    private String version;

    /**
     * Builds an immutable GAV object.
     *
     * @param groupId    the groupId of the maven object
     * @param artifactId the artifactId of the maven object
     * @param version    the version of the maven object
     */
    GAV(String groupId, String artifactId, String version) {
        this.groupId = groupId;
        this.artifactId = artifactId;
        this.version = version;
    }


    String getGroupId() {
        return groupId;
    }

    String getArtifactId() {
        return artifactId;
    }

    String getVersion() {
        return version;
    }

    static GAV of(Model model) {

        String groupId = model.getGroupId();
        String artifactId = model.getArtifactId();
        String version = model.getVersion();

        Parent parent = model.getParent();
        if (parent != null) {
            if (groupId == null) {
                groupId = parent.getGroupId();
            }
            if (version == null) {
                version = parent.getVersion();
            }
        }

        return new GAV(groupId, artifactId, version);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        GAV gav = (GAV) o;

        if (!groupId.equals(gav.groupId))
            return false;
        if (!artifactId.equals(gav.artifactId))
            return false;
        return version.equals(gav.version);
    }

    @Override
    public int hashCode() {
        int result = groupId.hashCode();
        result = 31 * result + artifactId.hashCode();
        result = 31 * result + version.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return String.format("%s:%s:%s", groupId, artifactId, version);
    }


}
